package com.example.mall.configuration;

import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter(){

//        初始化cors配置对象
        CorsConfiguration configuration=new CorsConfiguration();
//        允许跨域的域名，add可以可以多个，set只有一个
        configuration.addAllowedOriginPattern("*");
//        允许携带cookie
        configuration.setAllowCredentials(true);
        configuration.addAllowedMethod("*"); //表示所有请求方法都可以，例如get，post等等
        configuration.addAllowedHeader("*"); //允许携带任何头信息


//      是corsConfigurationSource的实现类
//        初始化cors配置源对象
        UrlBasedCorsConfigurationSource configurationSource=new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**",configuration);
//        返回一个接口
        return new CorsFilter(configurationSource);
    }
    /**
     * 配置转义字符,解决当请求路径中特殊字符，高版本tomcat解析失败的问题
     */
    @Bean
    public ServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory fa = new TomcatServletWebServerFactory();
        fa.addConnectorCustomizers(connector -> {
            connector.setProperty("relaxedQueryChars", "(),/:;<=>?@[\\]{}");
            connector.setProperty("rejectIllegalHeader", "false");
        });
        return fa;
    }
}