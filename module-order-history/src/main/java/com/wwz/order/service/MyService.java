package com.wwz.order.service;

import brave.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.cloud.sleuth.annotation.SpanTag;
import org.springframework.stereotype.Service;

/**
 * @author william.l
 * @version 2020/08/18 5:08 오후
 * @implNote
 */
@Slf4j
@Service
public class MyService {

    @Autowired
    Tracer tracer;

    @NewSpan("내정보보기")
    public String myInfo(@SpanTag(value = "myInfoName") String name) {
        log.info("## MyService:myInfo. name={}", name);

        String arg = tracer.toString();
        log.info("## MyService::tracer.toString()={}", arg);

        return "MyService::myInfo::name=" + name;
    }


}
