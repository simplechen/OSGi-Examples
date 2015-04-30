package me.laochen.osgi.shell.completer;

import java.util.List;

import org.apache.karaf.shell.console.Completer;
import org.apache.karaf.shell.console.completer.StringsCompleter;

public class ExecSimpleCompleter implements Completer {

	@Override
	public int complete(String buffer, int cursor, List candidates) {
		StringsCompleter delegate = new StringsCompleter();
		delegate.getStrings().add("realservices");
		delegate.getStrings().add("services");
		delegate.getStrings().add("hello");
		return delegate.complete(buffer, cursor, candidates);
	}
}
