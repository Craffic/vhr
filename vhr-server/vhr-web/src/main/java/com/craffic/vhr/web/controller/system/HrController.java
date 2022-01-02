package com.craffic.vhr.web.controller.system;

import com.craffic.vhr.model.Hr;
import com.craffic.vhr.model.RespBean;
import com.craffic.vhr.model.Role;
import com.craffic.vhr.service.HrService;
import com.craffic.vhr.service.RoleService;
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

    /**
     * 获取所有角色
     */
    @GetMapping("/query/all_role")
    public List<Role> getAllRoles() {
        return hrService.getAllRoles();
    }

    /**
     * 更新hr的roles角色
     */
    @PutMapping("/update/roles")
    public RespBean updateHrRole(Integer hrid, Integer[] rids) {
        if (hrService.updateHrRole(hrid, rids)) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    /**
     * 根据hrid删除hr
     */
    @DeleteMapping("/delete/{id}")
    public RespBean deleteHrById(@PathVariable Integer id) {
        Integer count = hrService.deleteHrById(id);
        if (count == 1) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
