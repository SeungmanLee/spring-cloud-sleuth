# spring-cloud-sleuth
spring cloud sleuth



* zipkin https://hub.docker.com/r/openzipkin/zipkin
* docker image download
```
docker pull openzipkin/zipkin
```

* local 에서 실행
```
docker run -d -p 9411:9411 openzipkin/zipkin
```


* DemoApplication 순서대로 실행.
* 각각의 Port 는 8081 / 8082 / 8083 으로 구분
* http 요청 : http://localhost:8081/send?port=8082&msg=yoyo%20william
* trace-id / span-id 가 각각 기록되는것 확인.
* trace-id 로 zipkin 에서 확인. 

```
# 요청
http://localhost:8081/send?port=8082&msg=yoyo%20william

# 8081 서버 로그 
2020-08-13 11:47:23.524  INFO [,96f07f63e91629a8,96f07f63e91629a8,true] 68791 --- [nio-8081-exec-1] c.e.demo.controller.HelloController      : ## uri=http://localhost:8082/hello?msg=from 8081 :: yoyo william
2020-08-13 11:47:23.647  INFO [,96f07f63e91629a8,96f07f63e91629a8,true] 68791 --- [nio-8081-exec-1] c.e.demo.controller.HelloController      : ## response=[8082] hello ~~ from 8081 :: yoyo william


# 8082 서버 로그
2020-08-13 11:47:23.621  INFO [,96f07f63e91629a8,3b1097ab2d156af6,true] 68798 --- [nio-8082-exec-1] c.e.demo.controller.HelloController      : ## message=from 8081 :: yoyo william

```



