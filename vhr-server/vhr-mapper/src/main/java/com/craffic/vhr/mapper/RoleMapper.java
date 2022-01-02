package com.craffic.vhr.mapper;

import com.craffic.vhr.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {

    List<Role> getHrRolesById(@Param("hrId") Integer hrId);

    List<Role> getAllRoles();

    int insert(Role record);

    int deleteByPrimaryKey(Integer id);
}