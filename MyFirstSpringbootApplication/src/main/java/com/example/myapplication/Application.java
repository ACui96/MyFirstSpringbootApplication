package com.example.myapplication;

import com.example.myapplication.customer.Customer;
import javafx.scene.Parent;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;


/**
 * @author acui
 */
@RestController//RestController 是一个定型注解，它将Example这个类变成了一个 web @Contrller ，这样的话Spring在处理web请求时会考虑这个类
@EnableAutoConfiguration//这个注解告诉Spring Boot 根据所添加的依赖来“猜测”应该如何配置Spring

public class Application {

    @RequestMapping("/")//带有路径信息，它告诉Spring任何带有 “/” 的HTTP请求应该映射到home方法
    String home() {
        return "Hello World!";
    }

    /**
     * 应用入口点，main方法通过调用run来委托Spring Boot的SpringApplication类
     * SpringApplication 引导应用启动Spring，Spring反过来又启动自动配置的TomcatWeb服务器
     * 我们需要将Example.class作为run方法的参数来钙塑SpringApplication那个是主要的Spring组件
     * args数组也传递过去来暴露任何命令行参数
     *
     */
    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

        Customer customer = new Customer();
        System.out.println(customer.getName());
    }
}
