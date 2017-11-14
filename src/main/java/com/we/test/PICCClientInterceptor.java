package com.we.test;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;
import java.util.List;

/**
 * 客户端自定义的soap拦截器，用来添加header信息
 * Created by mazhichao on 2017/8/29.
 */
public class PICCClientInterceptor extends AbstractSoapInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(PICCClientInterceptor.class);

    private static final String AUTHN_HEADER_NAME = "ngiamAuthn";

    private static final String APP_CODE_PARAM = "appCode";

    private static final String API_KEY_PARAM = "apiKey";

    private static final String NAMESPACE_URL = "http://api.ngiam.webservice.picc.com/";

    public PICCClientInterceptor() {
        super(Phase.PREPARE_SEND);
    }

    @Override
    public void handleMessage(SoapMessage message) throws Fault {
        List<Header> headers = message.getHeaders();
//        Map<String, List> headParam = (Map<String, List>) message.get(Message.PROTOCOL_HEADERS);
        /*
         * 代码生成了一个如下XML文档片段：
         * <ngiamAuthn xmlns="http://api.ngiam.webservice.picc.com/">
         *    <appCode>*****</appCode>
         *    <apiKey>*****</apiKey>
         * </ngiamAuthn>
         */
        headers.add(getAuthnHeader("ngiam-log", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCpHuGxMjyFVYaTagxpUMArPemfeSRo4od38xPIKCnyS50jjFM0Jjo/XDi820tbggC7o4DUNjkxq94GCB3tZ/PpKP3W2hTAtTQsGtPG2YDcq3RIDYy/CDDR/gjYn3hUY4URB695wXmQ6etIs6dtzh+Vr0/lpiCojPhxMKDEP2OENQIDAQAB"));
    }

    private Header getAuthnHeader(String appCode, String apiKey) {
        // SoapHeader部分待添加的节点
        QName qName = new QName(AUTHN_HEADER_NAME);
        Document doc = DOMUtils.createDocument();
        // 验证应用编码
        Element id = doc.createElement(APP_CODE_PARAM);
        id.setTextContent(appCode);
        // 验证APIKEY
        Element pwd = doc.createElement(API_KEY_PARAM);
        pwd.setTextContent(apiKey);
        // 创建认证Header根元素
        Element root = doc.createElementNS(NAMESPACE_URL, AUTHN_HEADER_NAME);
        root.appendChild(id);
        root.appendChild(pwd);
        // 创建SoapHeader内容
        SoapHeader header = new SoapHeader(qName, root);
        // 返回SoapHeader
        return header;
    }
}
