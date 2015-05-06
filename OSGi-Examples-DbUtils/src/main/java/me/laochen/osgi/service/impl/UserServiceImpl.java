package me.laochen.osgi.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Singleton;

import me.laochen.osgi.po.User;
import me.laochen.osgi.service.DataSourceService;
import me.laochen.osgi.service.UserService;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.ops4j.pax.cdi.api.OsgiService;

@Singleton
public class UserServiceImpl implements UserService {
	
	@Inject
	@OsgiService(filter="mode=me.laochen")
	private DataSourceService dataSourceService;
		
	public void setDataSourceService(DataSourceService dataSourceService) {
		this.dataSourceService = dataSourceService;
	}

	@Override
	public String get(Integer uid) {
		String reponseBody = "No Data";
		Connection conn = dataSourceService.getConnection();
		User user = null;
		try {
			String querySQL = "select * from user where 1 limit 1";
			if(uid>0){
				querySQL = String.format("select * from user where userId='%d'",Integer.valueOf(uid));
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
		return reponseBody;
	}
}
