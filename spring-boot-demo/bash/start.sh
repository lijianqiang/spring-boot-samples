#!/bin/sh
APP_DIR=/data/wwwroot/abtesting_api/api_9010
APP_NAME=nubia-abtest-api-1.4.0-RELEASE.jar
APP_CONF=$APP_DIR/application.properties
APP_LOG=/data/logs/boot/nubia-abtest-api-9010.log

echo "****** Starting ************"
echo "app: "$APP_DIR/$APP_NAME
echo "conf: "$APP_CONF
echo "log: "$APP_LOG
echo ""

JAVA_OPTS='-server -Xrs -Xmx1024m -Xms1024m -Xmn512m -XX:PermSize=256m -Xss256k -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 -XX:SurvivorRatio=8 -Xverify:none -Djava.net.preferIPv4Stack=true -Djava.security.egd=file:/dev/./urandom -Dfile.encoding=UTF8 -Dlog4j.log.port=8001'


nohup java $JVM_OPTS -jar $APP_DIR/$APP_NAME --spring.config.location=$APP_CONF > $APP_LOG &

echo "****** Start Finished ************"
