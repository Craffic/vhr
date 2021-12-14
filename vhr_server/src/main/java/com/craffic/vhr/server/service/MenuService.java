package com.craffic.vhr.server.service;

import com.craffic.vhr.server.dao.MenuMapper;
import com.craffic.vhr.server.domain.Hr;
import com.craffic.vhr.server.domain.Menu;
import com.craffic.vhr.server.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    MenuMapper menuMapper;

    /**
     * 根据hrid查询全部能访问的菜单数据
     * @return
     */
    public List<Menu> getMenusByHrId() {
        Hr curHr = (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return menuMapper.getMenusByHrId((curHr.getId()));
    }

    /**
     * 根据菜单id获取所需要角色
     */
    public List<Menu> getAllMenusWithRole(){
        return menuMapper.getAllMenusWithRole();
    }
}
