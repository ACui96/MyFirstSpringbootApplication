package com.aui.ticket;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1、将服务提供者注册到服务中心
 *  1、引入dubbo和zkcilent相关依赖
 *  2、配置dubbo扫描包和注册中心地址
 *  3、使用@Service发布服务
 */
@EnableDubbo
@SpringBootApplication
public class ProviderTicketApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderTicketApplication.class, args);
    }

}
