package com.craffic.vhr.server.controller.system;

import com.craffic.vhr.server.common.RespBean;
import com.craffic.vhr.server.domain.Hr;
import com.craffic.vhr.server.service.HrService;
import com.craffic.vhr.server.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/hr")
public class HrController {
    @Autowired
    HrService hrService;
    @Autowired
    RoleService roleService;

    @GetMapping("/query/all_hr")
    public List<Hr> getAllHrs(String keywords) {
        return hrService.getAllHrs(keywords);
    }

    /**
     * 更新hr
     */
    @PutMapping("/update")
    public RespBean updateHr(@RequestBody Hr hr) {
        if (hrService.updateHr(hr) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }
}
