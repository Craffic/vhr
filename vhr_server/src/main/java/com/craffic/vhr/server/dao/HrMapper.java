package com.craffic.vhr.server.dao;

import com.craffic.vhr.server.domain.Hr;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HrMapper {
    Hr loadUserByUsername(String userName);
}
