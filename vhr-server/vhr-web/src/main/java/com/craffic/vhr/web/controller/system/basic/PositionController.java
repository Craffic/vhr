package com.craffic.vhr.web.controller.system.basic;

import com.craffic.vhr.model.Position;
import com.craffic.vhr.model.RespBean;
import com.craffic.vhr.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/position")
public class PositionController {

    @Autowired
    private PositionService positionService;

    /**
     * 查询所有职位
     */
    @GetMapping("/queryAll")
    public List<Position> queryAllPositions(){
        return positionService.queryAllPositions();
    }

    /**
     * 新增职位
     */
    @PostMapping("/add")
    public Integer addPosition(@RequestBody Position position){
        return positionService.addPosition(position);
    }

    /**
     * 根据id删除职位
     */
    @DeleteMapping("/delete/{id}")
    public Integer deletePositionById(@PathVariable Integer id) {
        return positionService.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @DeleteMapping("/multi_del")
    public RespBean deletePositionsByIds(Integer[] ids) {
        if (positionService.multiDeletePositions(ids) == ids.length) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    /**
     * 修改职位
     */
    @PutMapping("/update")
    public RespBean updatePosition(@RequestBody Position position){
        if (positionService.updatePosition(position) == 1) {
            return RespBean.ok("修改成功！");
        }
        return RespBean.error("修改失败！");
    }
}
