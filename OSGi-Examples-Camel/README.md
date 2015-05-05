## Camel-Http Demo
需要安装 jetty,camel-jetty

#### Features Dependency
>1. karaf@root> features:install -v camel-jetty
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