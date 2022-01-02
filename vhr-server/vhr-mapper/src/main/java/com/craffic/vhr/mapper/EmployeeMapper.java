package com.craffic.vhr.mapper;

import com.craffic.vhr.model.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface EmployeeMapper {

    int deleteByPrimaryKey(Integer id);

    List<Employee> getEmployeeByPage(@Param("page") Integer page, @Param("size") Integer size, @Param("emp") Employee employee, @Param("beginDateScope") Date[] beginDateScope);

    Long getTotal(@Param("emp") Employee employee,@Param("beginDateScope") Date[] beginDateScope);

    int insertSelective(Employee record);

    Integer maxWorkID();

    int updateByPrimaryKeySelective(Employee record);

    Integer addEmps(@Param("list") List<Employee> list);
}
