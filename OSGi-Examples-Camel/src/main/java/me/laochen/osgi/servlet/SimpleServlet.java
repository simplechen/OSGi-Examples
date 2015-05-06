package me.laochen.osgi.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override  
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {  
        String data = "<h1>This is Servlet Example!</h1>";
        data += "<ul>";
        data += "<li>1. <a href='http://127.0.0.1:8182/hello' target='_blank'>Camel Hello Demo</a></li>";
        data += "<li>2. <a href='http://127.0.0.1:8182/dbutils' target='_blank'>DbUtils Demo</a></li>";
        data += "<li>3. <a href='http://127.0.0.1:8182/services/user?id=1' target='_blank'>Service Camel Demo</a></li>";
        data += "</ul>";
        resp.setContentType("text/html;charset=UTF-8");  
        resp.getOutputStream().write(data.getBytes("UTF-8"));  
    } 
}
