i1=`ps -ef|grep -E "xxx.jar"|grep -v grep|awk '{print $2}'`
message='start success'
if [ ! -z $i1 ] ; then
  message='start success'
  kill -9 $i1
fi
nohup java -jar xxx.jar > nohup.log 2>&1 &
i1=`ps -ef|grep -E "xxx.jar"|grep -v grep|awk '{print $2}'`
if [ ! -z $i1 ] ; then
  echo $message',pid='$i1
else
 echo 'start fail'
fi
