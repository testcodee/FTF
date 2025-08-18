package com.tft.forthefuture.Common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .addPathPatterns("/**") // 모든 경로에 인터셉터 적용
                .excludePathPatterns(
                        "/login",       // 로그인 페이지 제외
                        "/signup",      // 회원가입 페이지 제외
                        "/css/**",      // CSS 리소스 제외
                        "/js/**",       // JavaScript 리소스 제외
                        "/images/**",    // 이미지 리소스 제외
                        "/"             //
                );
    }
}
