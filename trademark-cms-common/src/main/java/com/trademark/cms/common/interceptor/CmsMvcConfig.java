package com.trademark.cms.common.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Description: web mvc配置
 * created by andy on 2019/8/5
 **/
@Configuration
public class CmsMvcConfig extends WebMvcConfigurationSupport {

    @Bean
    CmsLogInterceptor cmsLogInterceptor() {
        return new CmsLogInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(cmsLogInterceptor());
    }

    @Override
    protected void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        super.configureContentNegotiation(configurer);
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

    // 配置静态资源
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**")
                .addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/js/");
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/css/");
        registry.addResourceHandler("/img/**")
                .addResourceLocations("classpath:/img/");
        registry.addResourceHandler("/pages/**")
                .addResourceLocations("classpath:/pages/");
        registry.addResourceHandler("/font/**")
                .addResourceLocations("classpath:/font/");
//        registry.addResourceHandler(staticAccessPath)
//                .addResourceLocations("file:"+uploadFolder);
        super.addResourceHandlers(registry);
    }


}
