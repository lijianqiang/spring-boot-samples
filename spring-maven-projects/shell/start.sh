#!/bin/sh
APP_DIR=/data/wwwroot/springboot/client_9090
APP_NAME=aliyun-api-client-0.0.1-SNAPSHOT.jar
APP_CONF=$APP_DIR/application.properties
APP_LOG=/data/logs/springboot/client_9090.log

echo "****** Starting ************"
echo "app: "$APP_DIR/$APP_NAME
echo "conf: "$APP_CONF
echo "log: "$APP_LOG
echo ""

JAVA_OPTS='-server -Xrs -Xmx2g -Xms2g -Xmn512m -XX:PermSize=256m -Xss256k -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullC
ollection -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 -XX:SurvivorRatio=8 -Xverify:none -
Djava.net.preferIPv4Stack=true -Djava.security.egd=file:/dev/./urandom -Dfile.encoding=UTF8 -Dlog4j.log.port=8001'


#nohup java $JVM_OPTS -jar $APP_DIR/$APP_NAME --spring.config.location=$APP_CONF > $APP_LOG &
nohup java $JVM_OPTS -jar $APP_DIR/$APP_NAME > $APP_LOG &

echo "****** Start Finished ************"