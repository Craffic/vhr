package com.craffic.vhr.service;

import com.craffic.vhr.mapper.PositionMapper;
import com.craffic.vhr.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PositionService {

    @Autowired
    private PositionMapper positionMapper;

    public List<Position> queryAllPositions(){
        return positionMapper.queryAllPosition();
    }

    public Integer addPosition(Position position) {
        position.setEnabled(true);
        position.setCreateDate(new Date());
        return positionMapper.insertSelective(position);
    }

    public Integer deleteByPrimaryKey(Integer id) {
        return positionMapper.deleteByPrimaryKey(id);
    }

    public Integer multiDeletePositions(Integer[] ids) {
        return positionMapper.deletePositionsByIds(ids);
    }

    public Integer updatePosition(Position position) {
        return positionMapper.updatePosition(position);
    }

    public List<Position> getAllPositions() {
        return positionMapper.queryAllPosition();
    }
}
