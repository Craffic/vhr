package com.craffic.vhr.server.service;

import com.craffic.vhr.server.dao.MenuMapper;
import com.craffic.vhr.server.domain.Hr;
import com.craffic.vhr.server.domain.Menu;
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
}
