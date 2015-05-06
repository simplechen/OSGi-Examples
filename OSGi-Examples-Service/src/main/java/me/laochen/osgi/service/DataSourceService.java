package me.laochen.osgi.service;
import java.sql.Connection;

public interface DataSourceService {
	public Connection getConnection();
}