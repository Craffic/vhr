package com.craffic.vhr.mapper;

import com.craffic.vhr.model.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    /**
     * 用sql递归的方式来查询出所有部门节点的数据
     */
    List<Department> getAllDepartmentsByParentId(@Param("parentId") Integer parentId);

    void addDept(Department department);

    void deleteDepById(Department dep);

    List<Department> getAllDepartmentsWithOutChildren();
}
