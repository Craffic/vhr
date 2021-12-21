package com.craffic.vhr.server.dao;

import com.craffic.vhr.server.domain.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface EmployeeMapper {

    List<Employee> getEmployeeByPage(@Param("page") Integer page, @Param("size") Integer size, @Param("keyword") String keyword, @Param("emp") Employee employee, @Param("beginDateScope") Date[] beginDateScope);

    Long getTotal(@Param("keyword") String keyword, @Param("emp") Employee employee,@Param("beginDateScope") Date[] beginDateScope);
}
