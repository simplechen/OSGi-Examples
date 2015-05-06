package me.laochen.osgi.call;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;

import me.laochen.osgi.service.UserService;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.ops4j.pax.cdi.api.OsgiService;

@Singleton
public class ServicesHandler implements Processor{
	
	@Inject
	@OsgiService(filter="mode=me.laochen")
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		String reponseBody = "Invalid request";		
		HttpServletRequest request = exchange.getIn().getBody(HttpServletRequest.class);
		exchange.getOut().setHeader("Content-Type", "text/html;charset=utf-8");
		
		Integer id = (request.getParameter("id")!=null)?Integer.valueOf(request.getParameter("id")):0;
		reponseBody = userService.get(id);
		
		reponseBody+="<hr /><strong> <font color='red'>Useage: please visit:http://ip:prot/services/user?id=xxx</font></strong>";
		exchange.getOut().setBody(reponseBody);			
	}
}
