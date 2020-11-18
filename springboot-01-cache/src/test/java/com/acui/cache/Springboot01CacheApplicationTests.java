package com.acui.cache;

import com.acui.cache.bean.Employee;
import com.acui.cache.mapper.EmployeeMapper;
import javafx.application.Application;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class Springboot01CacheApplicationTests {
    @Resource
    EmployeeMapper employeeMapper;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    RedisTemplate<Object, Employee> empRedisTemplate;

    @Test
    void contextLoads() {
        Employee emp = employeeMapper.getEmpById(1);
        System.out.println(emp);
    }

    /**
     * Redis常见的五大类型
     * String List Set Hash ZSet
     * stringRedisTemplate.opsForValue String
     * stringRedisTemplate.opsForList list
     * stringRedisTemplate.opsForSet Set
     * stringRedisTemplate.opsForHash Hash
     * stringRedisTemplate.opsForZSet ZSet
     *
     */
    @Test
    public void test01() {
        //给redis中保存数据
        //stringRedisTemplate.opsForValue().append("msg","hello");
        /*String msg = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(msg);*/
        stringRedisTemplate.opsForList().leftPush("mylist", "1");
        stringRedisTemplate.opsForList().leftPush("mylist", "2");

    }

    /**
     * 测试保存对象
     */
    @Test
    public void test02() {
        Employee empById = employeeMapper.getEmpById(1);
        //默认如果保存对象使用jdk序列化机制，序列化后的数据保存到redis中
        redisTemplate.opsForValue().set("emp-01",empById);
        //1、将数据以json的方式保存
        empRedisTemplate.opsForValue().set("emp-01", empById);
    }

}
