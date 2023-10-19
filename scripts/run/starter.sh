test -e nohup.out && rm nohup.out
nohup java -jar ocb.jar --spring.profiles.active=prod &
echo $! > ocb_pid.txt
