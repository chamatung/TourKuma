package com.tour.kuma.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JwtTokenFilterConfig {

    private final JwtTokenFilter jwtTokenFilter;

    @Bean
    public FilterRegistrationBean<JwtTokenFilter> jwtTokenFilterRegistrationBean() {
        FilterRegistrationBean<JwtTokenFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(jwtTokenFilter);
        registrationBean.addUrlPatterns("/api/v1/*"); // 적용할 URL 패턴을 설정합니다.
        return registrationBean;
    }
}
