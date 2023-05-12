#!/bin/bash
source /etc/profile
current_path=`pwd`
case "`uname`" in
    Linux)
		bin_abs_path=$(readlink -f $(dirname $0))
		;;
	*)
		bin_abs_path=`cd $(dirname $0); pwd`
		;;
esac

BIN_DIR=$bin_abs_path
DEPLOY_DIR=$BIN_DIR/..
APP_NAME="backend"
JAR_NAME='backend.jar'
JAR_PATH="$DEPLOY_DIR/lib/"
CONF="$DEPLOY_DIR/conf/"
LOG_PATH="$DEPLOY_DIR/logs"
GC_PATH="$DEPLOY_DIR/logs/gc.log"
JAVA=java
JAVA_MEM_OPTS=" -server \
 -Xmx128m \
 -Xms128m \
 -Xmn128m \
 -XX:MetaspaceSize=128m \
 -XX:MaxMetaspaceSize=256m \
 -XX:-OmitStackTraceInFastThrow \
 -XX:+DisableExplicitGC \
 -XX:+UseConcMarkSweepGC \
 -XX:+CMSParallelRemarkEnabled \
 -XX:+UseCMSCompactAtFullCollection \
 -Xloggc:$GC_PATH \
 -XX:+PrintGCDetails \
 -XX:+PrintGCDateStamps \
 -XX:+PrintHeapAtGC \
 -XX:LargePageSizeInBytes=128m \
 -XX:+UseFastAccessorMethods \
 -XX:+UseCMSInitiatingOccupancyOnly \
 -XX:CMSInitiatingOccupancyFraction=70 "
JAVA_JMX_OPTS=" -Dcom.sun.management.jmxremote.port=9998 \
 -Dcom.sun.management.jmxremote.ssl=false \
 -Dcom.sun.management.jmxremote.authenticate=false"
JAVA_OPTS=" -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true "

start() {
 stop
 echo "sleep for stopping"
 sleep 2
 echo "start $APP_NAME "
 echo "exec command : nohup $JAVA $JAVA_OPTS $JAVA_MEM_OPTS -Dloader.path=${JAR_PATH}/libs/,$CONF -Dlocalcfg=true -DlogPath=${LOG_PATH} -jar -Dspring.config.location=$CONF $DEPLOY_DIR/lib/$JAR_NAME  > $LOG_PATH/$APP_NAME.out 2>&1 &"
 nohup $JAVA $JAVA_OPTS $JAVA_MEM_OPTS -Dloader.path=${JAR_PATH}/libs/,$CONF -Dlocalcfg=true -DlogPath=${LOG_PATH} -jar -Dspring.config.location=$CONF $DEPLOY_DIR/lib/$JAR_NAME  > $LOG_PATH/$APP_NAME.out 2>&1 &
 sleep 3
 boot_id=`ps -ef |grep java|grep $APP_NAME|grep -v grep|awk '{print $2}'`
 echo "app started pid = ${boot_id}"
}

stop(){
    boot_id=`ps -ef |grep java|grep $APP_NAME|grep -v grep|awk '{print $2}'`
    count=`ps -ef |grep java|grep $APP_NAME|grep -v grep|wc -l`
    if [ $count != 0 ];then
        kill -9 $boot_id
        echo "Stop $APP_NAME success..."
    else
    	echo "$APP_NAME is not running..."
    fi
}

status() {
  echo "=============================status=============================="
  boot_id=`ps -ef |grep java|grep $APP_NAME|grep -v grep|awk '{print $2}'`
  count=`ps -ef |grep java|grep $APP_NAME|grep -v grep|wc -l`
  if [ $count != 0 ];then
       echo "$APP_NAME is running,PID is $boot_id"
  else
       echo "$APP_NAME is not running!!!"
  fi
  echo "=============================status=============================="
}

info() {
  echo "=============================info=============================="
  echo "APP_LOCATION: $DEPLOY_DIR/lib/$JAR_NAME"
  echo "APP_NAME: $APP_NAME"
  echo "VM_OPTS: $JAVA_MEM_OPTS"
  echo "SPB_OPTS: $SPB_OPTS"
  echo "=============================info=============================="
}

help() {
   echo "start: start server"
   echo "stop: shutdown server"
   echo "status: display status of server"
   echo "info: display info of server"
   echo "help: help info"
}

case $1 in
start)
    start
    ;;
stop)
    stop
    ;;
status)
    status
    ;;
info)
    info
    ;;
help)
    help
    ;;
*)
    help
    ;;
esac
exit $?
