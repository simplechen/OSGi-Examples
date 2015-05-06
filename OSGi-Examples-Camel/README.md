# Camel-Http and HttpService

## Camel-Http Demo
需要安装 jetty,camel-jetty

#### Features Dependency
>1. karaf@root>features:install -v camel-jetty
 2. 编译并查看结果 
 mvn clean install
 
#### 部署并查看结果
> 
```
#cp current_project_dir/target/OSGi-Examples-Camel-0.0.1.jar servicemix_install_dir/deploy/
visit: http://127.0.0.1:8182/hello
```

#### 应用参数配置
1. 注意:jetty的host及port的配置可以在 servicemix_install_dir/etc/me.laochen.cfg中进行配置，详细查看根项目下面etc目录中的文件

2. 参考文章:
	[http://camel.apache.org/jetty.html]
	
3. 配置参数:
	[http://camel.apache.org/using-propertyplaceholder.html]
	
## [扩展功能实现Pax Web(OSGi HttpService)]
> 详细参看[http://servicemix.apache.org/docs/5.x/users-guide/http.html]
> [https://ops4j1.jira.com/wiki/display/paxweb/Documentation]


### 包依赖 http,war
>1. 安装
```
#karaf@root>features:install -v http
#karaf@root>features:install -v war
```
>2. 配置Web服务参数
```
#cd service_install_dir/etc
#cp cp org.ops4j.pax.web.cfg.empty.stub org.ops4j.pax.web.cfg
```
>3. 编辑servlet的实现->并配置src/main/resources/OSGI-INF/blueprint/blueprint.xml
```
<bean id="simpleServlet" class="me.laochen.osgi.servlet.SimpleServlet" ext:field-injection="true" />
<service interface="javax.servlet.http.HttpServlet" ref="simpleServlet">
	<service-properties>
		<entry key="alias" value="/home" />
	</service-properties>
</service>
```
>4. 访问http://localhost:8181/home