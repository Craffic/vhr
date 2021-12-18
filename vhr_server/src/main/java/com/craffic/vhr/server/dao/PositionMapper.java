package com.craffic.vhr.server.dao;

import com.craffic.vhr.server.common.RespBean;
import com.craffic.vhr.server.domain.Position;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PositionMapper {

    List<Position> queryAllPosition();

    Integer insertSelective(Position position);

    Integer deleteByPrimaryKey(Integer id);

    Integer deletePositionsByIds(@Param("ids") Integer[] ids);

    Integer updatePosition(Position position);
}
