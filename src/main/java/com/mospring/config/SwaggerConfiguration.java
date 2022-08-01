package com.mospring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger3 —— API 文档配置
 */
@Configuration
public class SwaggerConfiguration {

    public static final String TAG_ACCOUNT = "用户操作接口";
    public static final String TAG_ACCOUNT_DESCRIPTION = "与用户操作相关的路由";

    /**
     * 配置基本信息
     */
    private ApiInfo getApiInfo(){
        return new ApiInfoBuilder()
                .contact(new Contact("Airmomo", "https://github.com/airmomo", "xxx@email.com"))
                .title("MoSpring - 在线API接口文档")
                .description("这是网站的后端API文档，欢迎前端人员查阅！")
                .version("0.0.1")
                .build();
    }

    /**
     * 注册Swagger框架的Docket组件
     * 配置生效的API接口和API标签
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(getApiInfo())
                .tags(new Tag(TAG_ACCOUNT,TAG_ACCOUNT_DESCRIPTION))//注册API标签
                .select()//选择启用的API接口
                .apis(RequestHandlerSelectors.basePackage("com.mospring.controller"))//启用指定包下的所有API接口
                .build();
    }

}
