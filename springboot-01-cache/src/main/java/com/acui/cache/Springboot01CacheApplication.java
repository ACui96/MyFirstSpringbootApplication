package com.acui.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 一、搭建基本环境
 * 1、导入数据库文件 创建出department、employee表
 * 2、创建javabean封装数据
 * 3、整合mybatis操作数据路
 *  1.配置数据源
 *  2.使用注解版的Mybatis：
 *      1）、@Mappscan指定mapper接口所在的包
 *
 *  二、快速体验缓存
 *      步骤
 *          1、 开启基于注解的缓存
 *          2、 标注缓存注解即可
             * @Cacheable
             * @CaheEvit
             * @CachePut
 * 三、整合redis作为缓存
 * Redis是一个
 * 1、
 *
 *
 * 自定义CacheManager：
 *
 */


@MapperScan("com.acui.cache.mapper")
@SpringBootApplication
@EnableCaching
public class Springboot01CacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot01CacheApplication.class, args);
    }

}
