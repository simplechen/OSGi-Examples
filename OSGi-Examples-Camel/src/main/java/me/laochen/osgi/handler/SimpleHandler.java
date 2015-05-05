package me.laochen.osgi.handler;

import java.util.Map;

import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

@Singleton
public class SimpleHandler implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		HttpServletRequest request = exchange.getIn().getBody(HttpServletRequest.class);
		exchange.getOut().setHeader("Content-Type", "text/html;charset=utf-8");
		
		String reponseBody = "[Camel Base Servicemix-5.4.0 and jetty] <br /> Hello Jetty! <br />(<font color=red>notice:you can use param. for instance: http://<ip>:<port>/hello?uid=2&uname=simple</font>";		
		Map<String,String[]> params = request.getParameterMap();
		if(params.size()>0){
			StringBuffer sbuffer = new StringBuffer();
			for (String key : params.keySet()) {	
				StringBuffer tsb = new StringBuffer();
				for (String tval : params.get(key)) {
					tsb.append(tval);
				}
				sbuffer.append(key+"="+tsb.toString()+"&");
			}
			reponseBody = sbuffer.toString();
		}
		exchange.getOut().setBody(reponseBody);			
	}
}
