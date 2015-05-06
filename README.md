##The examples base on servicemix-5.4.0 

##install servicemix-5.4.0 (http://servicemix.apache.org)
wget http://apache.communilink.net/servicemix/servicemix-5/5.4.0/apache-servicemix-5.4.0.zip
unzip apache-servicemix-5.4.0.zip
cd apache-servicemix-5.4.0


#### start servicemix
./bin/servicemix


#### Blueprint Container
https://www.ibm.com/developerworks/cn/opensource/os-osgiblueprint/


### install bundle with version required
for example: features:install -v spring/3.2.4.RELEASE


### Question
>1. Install blueprint-maven-plugin
```
#wget [http://central.maven.org/maven2/org/apache/aries/blueprint/blueprint-maven-plugin/1.1.0/blueprint-maven-plugin-1.1.0.jar]
#mvn -X install:install-file  -Dfile=blueprint-maven-plugin-1.1.0.jar  -DgroupId=org.apache.aries.blueprint  -DartifactId=blueprint-maven-plugin -Dversion=1.1.0 -Dpackaging=jar
```
