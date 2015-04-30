package me.laochen.osgi.shell;

import java.util.List;

import me.laochen.osgi.service.IExtService;

import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.osgi.framework.ServiceReference;

@Command(scope = "simple", name = "exec", description = "exec command description")
public class ExecSimpleShell extends OsgiCommandSupport {

	@Argument(index = 0, name = "arg", description = "The command argument", required = false, multiValued = false)
	String arg = null;

	@Override
	protected Object doExecute() throws Exception {
		if("realservices".equals(arg)){
			System.err.println("start search real services");
			ServiceReference extserviceref=this.getBundleContext().getServiceReference("me.laochen.osgi.service.IExtService");
			IExtService extservice =(IExtService) this.getBundleContext().getService(extserviceref);
			
			List<String> services =extservice.getAllActualServices();
			for (String s : services) {
				System.err.println(s);
			}
		}
		else if ("services".equals(arg)) {// SHOW ALL SERVICES
			System.err.println("start search all services");
			ServiceReference[] services = this.getBundleContext()
					.getAllServiceReferences(null, null);
			for (ServiceReference serviceReference : services) {
				System.err.println(this.getBundleContext()
						.getService(serviceReference).getClass().getName());
			}
		} else if ("hello".equals(arg)) {
			System.err.println("Hello World");
		} else {
			System.err.println("--You call simple:hello and no use params");
		}
		return null;
	}
}
