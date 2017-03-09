#!/bin/sh  
          
rm -f tpid  
                    
nohup java -jar ~/bootspace/spring-boot-demo/target/spring-boot-demo-0.0.1-SNAPSHOT.jar  > /dev/null 2>&1 &  
                              
echo $! > tpid  
