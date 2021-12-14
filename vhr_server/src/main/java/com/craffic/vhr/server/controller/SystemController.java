package com.craffic.vhr.server.controller;

import com.craffic.vhr.server.domain.Menu;
import com.craffic.vhr.server.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SystemController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/menu")
    public List<Menu> getMenuByHrId(){
        return menuService.getMenusByHrId();
    }

    @GetMapping("/system/log")
    public String sysLog(){
        return "系统日志";
    }

    @GetMapping("/system/cfg")
    public String sysConfig(){
        return "系统配置";
    }
}
