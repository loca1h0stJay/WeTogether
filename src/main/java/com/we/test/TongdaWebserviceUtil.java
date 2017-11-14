package com.we.test;


import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

public class TongdaWebserviceUtil {
	private static final transient Logger logger = LoggerFactory.getLogger(TongdaWebserviceUtil.class);
	public static Call getCall(String url){
		Call call=null;
		try {
			Service service = new Service ( ) ;
			call = ( Call ) service.createCall ( );
			// 设置webservice服务的地址
			//call.setTargetEndpointAddress ( "http://57.0.6.3/webservice/PortalWebService/server_unified_run.php?wsdl" );
			//call.setTargetEndpointAddress ( "http://192.168.0.174/webservice/PortalWebService/server_unified_run.php?wsdl" ) ;
			// "http://57.25.2.51/webservice/PortalWebService/server_unified_run.php?wsdl"
			call.setTargetEndpointAddress (url);
			// 设置调用的参数及类型
			call.addParameter ("jsonParam" ,
					XMLType.XSD_STRING ,
					ParameterMode.IN ) ;
			call.setUseSOAPAction ( true ) ;
			// 设置返回参数类型
			call.setReturnType ( XMLType.XSD_STRING ) ;
			return call;
		} catch (ServiceException e) {
			logger.error(e.getMessage(),e);
		}
		return call;
		
	}
}
