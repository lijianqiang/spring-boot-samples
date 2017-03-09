#!/bin/sh
AGENT_VERSION="1.5.2"
AGENT_ID="springboot-127.0.0.1"
APPLICATION_NAME="spring-boot-demo"
AGENT_PATH="/data/program/pinpoint/pinpoint-agent-1.5.2"
CATALINA_OPTS="$CATALINA_OPTS -javaagent:$AGENT_PATH/pinpoint-bootstrap-${AGENT_VERSION}.jar"
CATALINA_OPTS="$CATALINA_OPTS -Dpinpoint.agentId=$AGENT_ID"
CATALINA_OPTS="$CATALINA_OPTS -Dpinpoint.applicationName=$APPLICATION_NAME"

nohup  java $CATALINA_OPTS -jar /data/program/pinpoint/spring-boot-pinpoint-0.0.1-SNAPSHOT.jar &
