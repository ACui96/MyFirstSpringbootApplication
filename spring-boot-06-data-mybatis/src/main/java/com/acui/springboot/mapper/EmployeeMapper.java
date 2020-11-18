package com.acui.springboot.mapper;

import com.acui.springboot.bean.Employee;

/**
 * @author acui
 */
//@Mapper或者@MapperScan将接口扫描装配到容器中
public interface EmployeeMapper {

    public Employee getEmpById(Integer id);

    public void insertEmployee(Employee employee);
}
