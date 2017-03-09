#!/bin/sh  
          
tpid=`cat tpid | awk '{print $1}'`  
tpid=`ps -aef | grep $tpid | awk '{print $2}' |grep $tpid`  
if [ ${tpid} ]; then  
    echo "App is running. pid is $tpid"  
    ps -ef | grep  $tpid
else  
    echo App is NOT running.  
fi  
