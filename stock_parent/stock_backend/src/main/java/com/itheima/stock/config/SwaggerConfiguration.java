package com.itheima.stock.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 04/02/2025 23:37
 * @Description : Swagger Configuration Class
 */

// configure the OpenAPI definition
//@OpenAPIDefinition(
//        info = @io.swagger.v3.oas.annotations.info.Info(
//                title = "Stock Management System",
//                version = "1.0",
//                description = "Stock Management System API Documentation"
//        )
//)

@Configuration
public class SwaggerConfiguration {

    @Bean
    /*
     * Custom OpenAPI configuration
     */
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Stock Management System")
                        .version("1.0")
                        .description("Stock Management System API Documentation")
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .email("admin@gmail.com")
                                .name("Tommy")
                                .url("https://www.*****.com")
                        )
                );
    }
}