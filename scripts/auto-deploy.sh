#!/bin/sh

CATALINA_HOME_C0=/usr/share/apache-tomcat-7.0.22
CATALINA_HOME_C1=/usr/share/apache-tomcat-7.0.22-c1
CATALINA_HOME_C2=/usr/share/apache-tomcat-7.0.22-c2

#Kill the monit process
/opt/monit-5.3.2/bin/monit quit
sleep 2

#Shutdown apache httpd
service httpd stop

sleep 2

service tomcat-cluster stop

sleep 5

#Clean history deployment
rm -f $CATALINA_HOME_C0/webapps/bocai.war
rm -rf $CATALINA_HOME_C0/webapps/bocai

rm -f $CATALINA_HOME_C1/webapps/bocai.war
rm -rf $CATALINA_HOME_C1/webapps/bocai

rm -f $CATALINA_HOME_C2/webapps/bocai.war
rm -rf $CATALINA_HOME_C2/webapps/bocai
#Clean finished

# svn checkout|css and js minify|build war
ant -f build.xml

#update static web images
rm -rf /var/www/static/bocai/images
cp -r /deploy/bocai/src/main/webapp/images /var/www/static/bocai/
sleep 1

#distribute war to tomcat cluster
cp /deploy/build/bocai.war $CATALINA_HOME_C0/webapps/
cp /deploy/build/bocai.war $CATALINA_HOME_C1/webapps/
cp /deploy/build/bocai.war $CATALINA_HOME_C2/webapps/
sleep 3

rm -rf /var/www/static/bocai/upload/original/*

service tomcat-cluster start

sleep 3 

service httpd start

/opt/monit-5.3.2/bin/monit

exit $?
