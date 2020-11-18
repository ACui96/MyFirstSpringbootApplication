package com.acui.cache.service;

import com.acui.cache.bean.Employee;
import com.acui.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@CacheConfig(cacheNames = "emp")
@Service
public class EmployeeService {

    @Resource
    EmployeeMapper employeeMapper;

    /**
     * 将方法的运行结果进行缓存，以后再要形同的数据，直接存缓存中获取，不用调用方法
     *
     * CacheManager管理多个Cache组件，对缓存的真正CRUD操作在Cache中
     * 几个属性：
     *  1、cacheNames/value：指定缓存的名字
     *  2、key：缓存数据使用的key，可以用它来指定，默认是使用方法参数的值 1-方法的返回值，
         *  编写SpEL: #id,参数id的值，  #a0 #p0 #root.args[0]
         *  keyGenerator : key 的生成器，可以自己指定key的生成器的组件id
         *  key/keyGenerator ： 二选一使用
         *  cacheManager ：指定缓存管理器；或者cacheResolver指定获取解析器
         *  condition： 指定符合条件的情况下才缓存
         *  unless：否定缓存，当unless指定的条件为true，方法的返回值就不会被缓存；可以获取到结果进行判断 e.g. unless = "#result==null"
         *  sync:是否使用异步模式
     *原理：
     *  1、自动配置类 CacheAutoConfiguration
     *  2、缓存的配置类
         *  0 = "org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration"
         * 1 = "org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration"
         * 2 = "org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration"
         * 3 = "org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration"
         * 4 = "org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration"
         * 5 = "org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration"
         * 6 = "org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration"
         * 7 = "org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration"
         * 8 = "org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration"
         * 9 = "org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration"
     * 3、哪个配置类默认生效 :SimpleCacheConfiguration
     * 4、给容器中注册了一个ConcurrentMapCache:ConcurrentMapCacheManager
     * 5、可以获取和创建ConcurrentMapCache类型的缓存组件；它的作用将数据保存在ConcurrentMap中；
     * 6、运行流程：
     *    @Cacheable
     *      1、方法运行之前，先去查询Cache（缓存组件），按照cacheNames指定的名字获取
     *      （CacheManager先获取相应的缓存），第一次获取缓存如果没有cache组件会自动创建
     *      2、去Cache中
     * @param id
     * @return
     */
    @Cacheable(cacheNames = {"emp"}/*,keyGenerator = "myKeyGenerator",condition = "#a0>1", unless = "#a0==2"*/)
    public Employee getEmp(Integer id) {
        System.out.println("查询" + id + "号员工");
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }

    /**
     * CachePut: 及调用方法，有封信缓存
     * 修改了数据库的某个数据，同时跟新缓存；‘
     * 运行时机：
     *  1、先调用目标方法
     *  2、将目标方法的结果缓存起来
     *
     */
    @CachePut(value = "emp",key = "#result.id")
    public Employee updateEmp(Employee employee) {
        System.out.println("updateEmp"+employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }

    /**
     * @CacheEvict:缓存清除
     * key:指定要清除的数据
     */
    @CacheEvict(value = "emp"/*,key = "#id"*/,allEntries = true,beforeInvocation = true)
    public void deleteEmp(Integer id) {
        System.out.println("deleteEmp"+id);
        //employeeMapper.deleteEmpById(id);
        int i = 10 / 0;
    }

    @Caching(
            cacheable = {
                    @Cacheable(/*value = "emp",*/key = "#lastName")
            },
            put = {
                    @CachePut(/*value = "emp",*/key = "#result.id"),
                        @CachePut(/*value = "emp",*/key = "#result.email")
            }

    )
    public Employee getEmpByLastName(String lastName) {
        return employeeMapper.getEmpByLastName(lastName);
    }
}
