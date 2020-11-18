package com.acui.springboot04web.config;

import com.acui.springboot04web.component.MyLocalResolver;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author acui
 */
//全面接管 Springmvc。使spring-boot的自动配置失效
//@EnableWebMvc

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //super.addViewControllers(registry)
        //浏览器发送/atguigu 请求来到 success
        registry.addViewController("/atguigu").setViewName("success");
    }

    //注册拦截器

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //super.addInterceptors(registry)
        //静态资源 spring-boot已经做好了静态资源映射
//        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/index.html", "/", "/user/login","/templates/","/webjars/**","/asserts/**");
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {

        return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/login.html").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }
        };
    }
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocalResolver();
    }
}
