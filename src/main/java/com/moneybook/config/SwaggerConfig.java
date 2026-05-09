package com.moneybook.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger(SpringDoc OpenAPI) 설정
 * API 문서의 기본 정보를 구성합니다.
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Money Book API")
                        .description("가계부 앱 REST API 문서")
                        .version("1.0.0"));
    }
}
