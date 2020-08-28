package com.wwz.mainpage.service;

import brave.Span;
import brave.Tracer;
import brave.Tracing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.SpanName;
import org.springframework.cloud.sleuth.annotation.ContinueSpan;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.cloud.sleuth.annotation.SpanTag;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author william.l
 * @version 2020/08/18 5:09 오후
 * @implNote
 */
@Slf4j
@Service
public class HistoryService {

    @Autowired
    Tracer tracer;

    @NewSpan("히스토리-조회")
//    @ContinueSpan(log = "히스토리-조회-continueSpan")
    public List<String> myHistories(@SpanTag(value = "HistoryName") String name) {
        log.info("## HistoryService::myHistories. name={}", name);
        ArrayList<String> results = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            results.add("history::" + i + "," + name);
        }

        log.info("## HistoryService::tracer.toString()={}", tracer.toString());

        return results;

    }


}
