package com.we.test;

import org.apache.axis.client.Call;
import org.apache.axis.message.SOAPHeaderElement;

import javax.xml.soap.*;
import java.rmi.RemoteException;

public class TestWebService {
    public static void main(String[] args) {
        Call call=TongdaWebserviceUtil.getCall("http://admin.jingantech.com/ngiam-rst/services/wsapi?wsdl");
//        QName qn = new QName("http://ws.imp.ngiam.jingantech.com/","apiKey");
////        call.addParameterAsHeader(qn, XMLType.XSD_STRING, ParameterMode.IN ,ParameterMode.IN);
//
//        call.setOperationName (qn) ;
//        Name headerName = new PrefixedQName(qn);





        try {
            SOAPFactory soapFactory=SOAPFactory.newInstance();
            SOAPElement element=soapFactory.createElement("Security");
            SOAPElement apiKeyElement=soapFactory.createElement("apiKey");
            apiKeyElement.addTextNode("123");
            element.addChildElement(apiKeyElement);
            SOAPHeaderElement head = new SOAPHeaderElement(element);
            call.addHeader(head);
            call.setOperationName("testWs");
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        try {
            String wsResult = ( String) call.invoke ( new Object[] {
                    "qweqweqwe"
            } ) ;
            System.out.println(wsResult);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
//        String xmlStr="123";
//        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
//        Client client = dcf.createClient("http://admin.jingantech.com/ngiam-rst/services/wsapi?wsdl");
//        //添加输出拦截器
//        client.getOutInterceptors().add(new PICCClientInterceptor());
//        //url为调用webService的wsdl地址
//        QName name=new QName("http://ws.imp.ngiam.jingantech.com/","testWs");
//        //paramvalue为参数值
//        Object[] objects = new Object[0];
//        try {
//            objects = client.invoke(name,xmlStr);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        //调用web Service//输出调用结果
//        System.out.println("客户端输出结果："+objects[0].toString());
    }
}
