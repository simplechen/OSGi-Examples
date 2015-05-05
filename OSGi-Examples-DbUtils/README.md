## 通过Camel-Jetty包装,调用DbUtils实现的数据简单操作 ##
实现通过camel包装http对数据库进行直接的访问

本项目依赖于mysql,osgi,camel,dbutils,具体参看pom.xml中的maven包依赖信息

数据源配置
#karaf@root>jdbc:create -t MySQL -url jdbc:mysql://127.0.0.1/osgi -u root -p 123456 test
参数手工调整 vi servicemix_install_dir/deploy/datasource-test.xml


编译安装:
1.mvn clean install
2.将生成的jar:OSGi-Examples-DbUtils-0.0.1.jar复制至servicemix_install_dir/deploy目录下

依赖处理
1. 安装mysql对应java版本的驱动
从官网http://dev.mysql.com/downloads/connector/j/ 下载mysql驱动 5.1.35 并将此jar包移至servicemix_install_dir/deploy目录中
2.安装camel 参看OSGi-Examples-Camel中描述


访问地址:http://<ip>:<port>/dbutils[?id=xxx] 其中ip和port的配置在src/main/resources/OSGI-INF/blueprint/blueprint.xml中
默认为本地机IP及8182端口，如果是在本机访问对应的地址是http://localhost:8182/dbutils

DbUtils examples migrate from :http://www.codesuggestions.com/java/apache-commons-dbutils-tutorial/