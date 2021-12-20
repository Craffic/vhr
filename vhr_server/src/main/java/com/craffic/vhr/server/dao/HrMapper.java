package com.craffic.vhr.server.dao;

import com.craffic.vhr.server.domain.Hr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HrMapper {
    Hr loadUserByUsername(String userName);

    List<Hr> getAllHrs(@Param("hrid") Integer hrid,  @Param("keywords") String keywords);

    Integer updateByPrimaryKeySelective(Hr hr);

    Integer deleteByPrimaryKey(Integer id);
}
