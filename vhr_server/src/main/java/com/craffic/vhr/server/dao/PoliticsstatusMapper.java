package com.craffic.vhr.server.dao;

import com.craffic.vhr.server.domain.Politicsstatus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PoliticsstatusMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Politicsstatus record);

    int insertSelective(Politicsstatus record);

    Politicsstatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Politicsstatus record);

    int updateByPrimaryKey(Politicsstatus record);

    List<Politicsstatus> getAllPoliticsstatus();
}