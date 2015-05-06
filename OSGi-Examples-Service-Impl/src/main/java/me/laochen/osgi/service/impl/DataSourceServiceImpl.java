package me.laochen.osgi.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.sql.DataSource;

import me.laochen.osgi.service.DataSourceService;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.ops4j.pax.cdi.api.Properties;
import org.ops4j.pax.cdi.api.Property;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

@OsgiServiceProvider(classes={DataSourceService.class})
@Properties({@Property(name="mode",value="me.laochen")})
@Singleton
@Named("dataSourceService")
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
