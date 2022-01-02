package com.craffic.vhr.service;

import com.craffic.vhr.mapper.DepartmentMapper;
import com.craffic.vhr.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    public List<Department> queryAllDepartments() {
        return departmentMapper.getAllDepartmentsByParentId(-1);
    }

    public Department addDept(Department department) {
        department.setEnabled(true);
        departmentMapper.addDept(department);
        return null;
    }

    public void deleteDepById(Department dep) {
        departmentMapper.deleteDepById(dep);
    }

    public List<Department> getAllDepartmentsWithOutChildren() {
        return departmentMapper.getAllDepartmentsWithOutChildren();
    }
}
