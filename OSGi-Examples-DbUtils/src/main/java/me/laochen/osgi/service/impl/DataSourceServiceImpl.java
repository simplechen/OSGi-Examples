package me.laochen.osgi.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;

import me.laochen.osgi.service.DataSourceService;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

@Singleton
public class DataSourceServiceImpl implements DataSourceService {

	@Inject
	private BundleContext bundleContext;
	
	public void setBundleContext(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}

	@Override
	public Connection getConnection() {
		ServiceReference dsref = this.bundleContext.getServiceReference(javax.sql.DataSource.class.getName());
		if(dsref!=null){
			DataSource ds = (DataSource) this.bundleContext.getService(dsref);//获取真正的服务
			try {
				Connection conn = ds.getConnection();
				return conn;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
