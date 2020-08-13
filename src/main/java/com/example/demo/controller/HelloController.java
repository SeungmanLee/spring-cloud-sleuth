package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author william.l
 * @version 2020/08/13 11:11 오전
 * @implNote
 */
@Slf4j
@RestController
public class HelloController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/hello")
    public String hello(@RequestParam String msg) {
        String portName = System.getProperty("server.port");
        log.info("## message={}", msg);
        return "[" + portName + "] hello ~~ " + msg;
    }

    @RequestMapping("/send")
    public String send(@RequestParam int port,
                       @RequestParam String msg) {
        return sendMessageTo(port, msg);
    }

    private String sendMessageTo(int port, String msg) {
        String portName = System.getProperty("server.port");

        String uri = "http://localhost:" + port + "/hello?msg=" + "from " + portName + " :: " + msg;
        log.info("## uri={}", uri);
        
        String response = restTemplate.getForObject(uri, String.class);

        log.info("## response={}", response);
        return response;
    }


}
