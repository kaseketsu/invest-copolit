package com.flower.invest_copolit.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI (Swagger) 配置类
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Invest Copilot API 接口文档")
                        .version("1.0.0")
                        .description("投资助手(Invest Copilot)的后端 API 文档，包含资产、行情、内容、AI等领域接口。")
                        .contact(new Contact().name("copolit")));
    }
}