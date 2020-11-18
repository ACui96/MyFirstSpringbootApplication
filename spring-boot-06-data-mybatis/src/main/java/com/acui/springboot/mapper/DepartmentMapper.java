package com.acui.springboot.mapper;

import com.acui.springboot.bean.Department;
import org.apache.ibatis.annotations.*;

/**
 指定这是一个操作数据库的mapper
 * @author acui
 */
//@Mapper
public interface DepartmentMapper {
    /**
     * 根据id查询部门
     * @param id
     * @return
     */
    @Select("select * from department where id=#{id}")
    public Department getDeptById(Integer id);

    /**
     * 根据id删除部门
     * @param id
     * @return
     */
    @Delete("delete from department where id=#{id}")
    public int deleteDeptById(Integer id);

    /**
     * 新增部门
     *
     * @param department
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into department(department_name) values(#{department_name})")
    public int insertDept(Department department);
    /**
     * 更新部门
     * @param department
     * @return
     */
    @Update("update department set department_name=#{department_name} where id=#{id}")
    public int updateDept(Department department);
}
