一个简单的指令，具体业务在指令接口中进行处理

''' 为了查找所有服务的真实名字,需要安装jndi'''

karaf>feature:install jndi
karaf>la|grep -i jndi
然后找到 Apache Karaf :: JNDI :: Core 对应的id
karaf>bundle:services id