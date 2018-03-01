set MAVEN_HOME=U:\T78580A\POC_Workspace\POC_wicket\apache-maven-2.0.9
set JAVA_HOME=U:\T78580A\Softwares\java\jdk1.6.0_14
set ANT_HOME=U:\T78580A\Softwares\apache-ant-1.7.1
set Path=%JAVA_HOME%\bin;%MAVEN_HOME%\bin;%ANT_HOME%\bin;%Path%
set MAVEN_OPTS=-Xmx512m -XX:MaxPermSize=128m


call mvn clean package -Dmaven.test.skip=true