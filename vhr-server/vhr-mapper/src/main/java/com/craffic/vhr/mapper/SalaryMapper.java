package com.craffic.vhr.mapper;

import com.craffic.vhr.model.Salary;

import java.util.List;

public interface SalaryMapper {

    List<Salary> getAllSalaries();

    int insertSelective(Salary record);

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Salary record);
}
