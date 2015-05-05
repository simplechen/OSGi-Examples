package me.laochen.osgi.handler;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import me.laochen.osgi.po.User;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class DbutilsHandler implements Processor{
	
	private BundleContext bundleContext;
	
	public void setBundleContext(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		String reponseBody = "Invalid request";		
		HttpServletRequest request = exchange.getIn().getBody(HttpServletRequest.class);
		exchange.getOut().setHeader("Content-Type", "text/html;charset=utf-8");
		ServiceReference dsref = this.bundleContext.getServiceReference(javax.sql.DataSource.class.getName());
		
		if(dsref!=null){
			DataSource ds = (DataSource) this.bundleContext.getService(dsref);//获取真正的服务
			Connection conn = ds.getConnection();	
			User user = null;
			try {
				String querySQL = "select * from user where 1 limit 1";
				if(request.getParameter("id")!=null){
					querySQL = String.format("select * from user where userId='%d'",Integer.valueOf(request.getParameter("id")));
				}
				System.err.println("execute sql:"+querySQL);
	            QueryRunner query = new QueryRunner();
	            user = (User) query.query(conn, querySQL, new BeanHandler<User>(User.class));
	            reponseBody = String.format("%s", user.toString());
	            
	            System.err.println(reponseBody);
	        } catch (SQLException se) {
	            se.printStackTrace();
	        } finally {
	            DbUtils.closeQuietly(conn);
	        }
		}
		reponseBody+="<hr /><strong> <font color='red'>Useage: please visit:http://ip:prot/dbutils?id=xxx</font></strong>";
		exchange.getOut().setBody(reponseBody);			
	}
}
