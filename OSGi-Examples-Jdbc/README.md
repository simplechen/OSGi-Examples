## 使用Karaf中jdbc features

#### Features Dependency
1. 安装jdbc
```
#karaf>features:install -v jdbc
#karaf>la|grep -i jdbc
#然后找到 Apache Karaf :: JDBC :: Core 对应的id
#karaf>bundle:services id
```

2. 安装MySQL-Java 驱动
>1. 从官网［http://dev.mysql.com/downloads/connector/j/] 下载mysql驱动
>2. 将下载下来的jar包放置servicemix_install_dir/deploy目录
>3. 检查是否安装成功
>>karaf>la|grep -i mysql 
>>如果发现Oracle Corporation's JDBC Driver for MySQL 说明安装成功


#### 配置一个数据源,连接数据库test
1. karaf>jdbc:create -t MySQL -u root test
>需要创建一个名称test的数据库及表user，表结构在最外层的database目录下面

执行上面的指令karaf将会在 servicemix_install_dir/deploy 目录下面创建一个datasource-test.xml文件

>修改该文件内容如下：
```
<?xml version="1.0" encoding="UTF-8"?>
<blueprint xmlns="http://www.osgi.org/xmlns/blueprint/v1.0.0">
    <bean id="dataSource" class="com.mysql.jdbc.jdbc2.optional.MysqlDataSource">
        <property name="url" value="jdbc:mysql://127.0.0.1:3306/test"/>
        <property name="user" value="root"/>
        <property name="password" value=""/>
    </bean>
    <service interface="javax.sql.DataSource" ref="dataSource">
        <service-properties>
            <entry key="osgi.jndi.service.name" value="jdbc/test"/>
        </service-properties>
    </service>
</blueprint>
```

2. 查看是否生效
> 前提是mysql服务器确实已经启动，并且有一个test的数据库,使用下面的指令查看状态 
```
#karaf>jdbc:datasources
输出内容：
Name           | Product | Version    | URL                              | Status
---------------------------------------------------------------------------------
jdbc/test, 838 | MySQL   | 5.5.28-log | jdbc:mysql://127.0.0.1:3306/test | OK  
```

3. 测试jdbc指令
```
#karaf>jdbc:tables jdbc/test
REF_GENERATION | TYPE_NAME | TABLE_NAME  | TYPE_CAT | REMARKS | TYPE_SCHEM | TABLE_TYPE | TABLE_SCHEM | TABLE_CAT | SELF_REFERENCING_COL_NAME
---------------------------------------------------------------------------------------------------------------------------------------------
               |           | documents   |          |         |            | TABLE      |             | test      |                          
               |           | name        |          |         |            | TABLE      |             | test      |                          
               |           | sbtest_part |          |         |            | TABLE      |             | test      |                          
               |           | tbl01       |          |         |            | TABLE      |             | test      |                          
               |           | user        |          |         |            | TABLE      |             | test      |
               
```