package com.op.gg.ogj.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//https://velog.io/@sa833591/Swagger-API-%EB%AC%B8%EC%84%9C-%EC%9E%90%EB%8F%99%ED%99%94
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket swaggerApi(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(swaggerInfo())
                //.ignoredParameterTypes(Pageable.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.op.gg.ogj"))
                .paths(PathSelectors.ant("/api/**"))
                .build();
    }

        private ApiInfo swaggerInfo() {
            return new ApiInfoBuilder().title("Spring API Documentation")
                    .description("앱 개발시 사용되는 서버 API에 대한 연동 문서입니다")
                    .license("jpa").licenseUrl("http://jpa").version("1").build();
        }

}
