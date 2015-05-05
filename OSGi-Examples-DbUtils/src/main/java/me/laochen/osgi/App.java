package me.laochen.osgi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import me.laochen.osgi.po.User;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) {
        Connection conn = null;
        String url = "jdbc:mysql://127.0.0.1:3306/osgi";
        String driver = "com.mysql.jdbc.Driver";
        String usr = "root";
        String pwd = "";
        User user = null;
        try {
            DbUtils.loadDriver(driver);
            conn = DriverManager.getConnection(url, usr, pwd);
            QueryRunner query = new QueryRunner();
            user = (User) query.query(conn, "select * from user where userId=3", new BeanHandler(User.class));
            System.out.println("User Object::  " + user.getUserId() + "\t" + user.getFirstName() + "\t" + user.getLastName() + "\t" + user.getEmailId());
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            //Closing the connection quietly, means it will handles the SQLException
            DbUtils.closeQuietly(conn);
        }
    }
}
