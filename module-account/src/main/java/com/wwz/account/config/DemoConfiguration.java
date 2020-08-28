package com.wwz.account.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author william.l
 * @version 2020/08/13 11:45 오전
 * @implNote
 */
@Configuration
public class DemoConfiguration {

    /**
     * bean 으로 등록해야만, sleuth 에서 태그를 엮어줌.
     *
     * @return
     */
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
