package me.laochen.osgi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Enumeration;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

/**
 * Hello world!
 *
 */
public class App implements BundleActivator, ServiceListener {

	@Override
	public void start(BundleContext context) throws Exception {
		System.err.println("Shell-start....");
		
		System.err.println("start--获取指定目录下面的子目录或文件");
		Enumeration<String> urls = context.getBundle().getEntryPaths("data");
		while(urls.hasMoreElements()){
			String url = urls.nextElement();
			System.err.println(url);
		}
		System.err.println("stop--获取指定目录下面的子目录或文件");
		
		//读取指定的文件
//		context.getBundle().getEntry(path)
		
		/*
		 * http://stackoverflow.com/questions/6474634/how-do-i-access-a-file-inside
		 * -an-osgi-bundle
		 * https://www.cct.lsu.edu/~rguidry/ecl31docs/api/org/eclipse
		 * /osgi/framework/adaptor/BundleData.html
		 * http://docs.jboss.org/osgi/jboss
		 * -osgi-1.0.0.Beta7/apidocs/org/jboss/osgi/testing/OSGiBundle.html
		 * 
		 * 也可以参考：http://rongmayisheng.com/post/%E6%BA%90%E7%A0%81%E8%A7%A3%E6%
		 * 9E%90%E4%B9%8B%E8%AE%BF%E9%97%AEosgi-felix-bundle%E4%B8%AD%E7%9A%84%E6%96%87%E4%BB%B6%E5%92%8C%E8%B5%84%E6%BA%90
		 */
		
		System.err.println("开始读取Bundle中的资源文件");
		URL url = context.getBundle().getResource("app.txt");
		System.out.println(url.toString());



		BufferedReader br = new BufferedReader(new InputStreamReader(url
				.openConnection().getInputStream()));
		while (br.ready()) {
			System.out.println(br.readLine());
		}
		br.close();

		System.err.println("测试在data/cache/bundlexxx/data下面创建文件的功能");
		File dataFile = context.getDataFile("");
		
		String dataFileStr = dataFile.getAbsolutePath();
		System.out.println(dataFileStr);
		
		dataFile = context.getDataFile("data.txt");
		System.out.println(dataFile.getAbsolutePath());
		
		if (dataFile.exists()) {
			try {
				br = new BufferedReader(new FileReader(dataFile));
				while (br.ready()) {
					System.out.println(br.readLine());
				}
			} finally {
				br.close();
			}
		} else {
			dataFile.createNewFile();//创建新文件
			System.out.println();// 输出：true
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter(dataFile));
				bw.write("this is the created dataFile");
			} finally {
				bw.close();
			}
			dataFile = context.getDataFile("data.txt");
			System.out.println(dataFile.getAbsolutePath());
			System.out.println(dataFile.exists());// 输出：true
			try {
				br = new BufferedReader(new FileReader(dataFile));
				while (br.ready()) {
					System.out.println(br.readLine());// 输出：this is the created
				}
			} finally {
				br.close();
			}
		}
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.err.println("Shell stop");
	}

	@Override
	public void serviceChanged(ServiceEvent event) {
		System.err.println("Shell-service-changed--");
	}
	
}
