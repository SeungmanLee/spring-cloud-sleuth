package com.wwz.mainpage.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.cloud.sleuth.annotation.SpanTag;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author william.l
 * @version 2020/08/18 5:08 오후
 * @implNote
 */
@Slf4j
@Service
public class MainPageService {


    private final MyService myService;
    private final HistoryService historyService;

    public MainPageService(MyService myService, HistoryService historyService) {
        this.myService = myService;
        this.historyService = historyService;
    }

    @NewSpan("메인페이지")
    public HashMap<String, Object> mainPageInfo(@SpanTag(value = "mainPageInfoName") String name) {
        String my = myService.myInfo(name);

        List<String> histories = historyService.myHistories(name);

        HashMap<String, Object> result = new HashMap<>();
        result.put("my", my);
        result.put("history", histories);

        return result;

    }

}
