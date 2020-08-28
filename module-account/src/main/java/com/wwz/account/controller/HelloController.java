package com.wwz.account.controller;

import com.wwz.account.service.MainPageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.SecureRandom;
import java.util.HashMap;

/**
 * @author william.l
 * @version 2020/08/13 11:11 오전
 * @implNote
 */
@Slf4j
@RestController
public class HelloController {

    private final MainPageService mainPageService;


    @Autowired
    private RestTemplate restTemplate;

    public HelloController(MainPageService mainPageService) {
        this.mainPageService = mainPageService;
    }

    @RequestMapping("/hello")
    public String hello(@RequestParam String msg) {
        String portName = System.getProperty("server.port");
        log.info("## message={}", msg);

        SecureRandom random = new SecureRandom();
        int i = random.nextInt();
        int nextInt = i % 3;
        if (nextInt == 0) {
            throw new RuntimeException("## exception. " + i);
        }

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

    @RequestMapping("/my")
    public HashMap<String, Object> my(@RequestParam String name) {
        log.info("## HelloController::my={}", name);
        HashMap<String, Object> result = mainPageService.mainPageInfo(name);
        log.info("## HelloController::result={}", result);
        return result;
    }


}
