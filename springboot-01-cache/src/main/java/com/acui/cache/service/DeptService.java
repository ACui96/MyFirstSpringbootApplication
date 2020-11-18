package com.acui.cache.service;

import com.acui.cache.bean.Department;
import com.acui.cache.mapper.DepartmentMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DeptService {
    @Resource
    DepartmentMapper departmentMapper;
    @Cacheable(cacheNames = "dept")
    public Department getDeptById(Integer id) {
        System.out.println("查询部门"+id);
        Department department = departmentMapper.getDeptById(id);
        return department;
    }
}
