package me.laochen.osgi.service.impl;

import java.util.ArrayList;
import java.util.List;

import me.laochen.osgi.service.IExtService;

import org.apache.aries.proxy.ProxyManager;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

public class ExtServiceImpl implements IExtService {

	private BundleContext bundleContext;
	private ProxyManager proxyManager;

	public BundleContext getBundleContext() {
		return bundleContext;
	}

	public void setBundleContext(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}

	public ProxyManager getProxyManager() {
		return proxyManager;
	}

	public void setProxyManager(ProxyManager proxyManager) {
		this.proxyManager = proxyManager;
	}

	@Override
	public List<String> getAllActualServices() {

		List<String> result = new ArrayList<String>();
		ServiceReference[] services;
		try {
			services = this.bundleContext.getAllServiceReferences(null, null);
			if (services != null) {
				for (ServiceReference service : services) {
					Object actualService = this.bundleContext.getService(service);
					if (this.proxyManager.isProxy(actualService)) {
						actualService = this.proxyManager.unwrap(actualService).call();
					}
					result.add(actualService.getClass().getName());
				}
			}
		} catch (InvalidSyntaxException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
