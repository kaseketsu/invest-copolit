package com.flower.invest_copolit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring Web MVC 配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 匹配所有的路径
                .allowedOriginPatterns("*") // 允许所有跨域请求的来源
                .allowCredentials(true) // 允许携带 Cookie
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH") // 允许的请求方式
                .allowedHeaders("*") // 允许的 Header
                .maxAge(3600); // 跨域允许时间
    }
}