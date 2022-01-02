package com.craffic.vhr.web.config;


import com.craffic.vhr.model.Menu;
import com.craffic.vhr.model.Role;
import com.craffic.vhr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 根据用户传过来的请求地址，分析出请求需要的角色
 */
@Configuration
public class CustFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private MenuService menuService;

    AntPathMatcher matcher = new AntPathMatcher();

    /**
     *
     * @param object
     * @return Collection: 当前请求所需要的角色
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // object是一个FilterInvocation对象，可以从FilterInvocation中获取到请求url
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        // 获取所有菜单，遍历所有菜单
        List<Menu> menus = menuService.getAllMenusWithRole();
        for (Menu menu : menus) {
            if (matcher.match(menu.getUrl(), requestUrl)) {
                // 匹配上就获取菜单对应的角色集合
                List<Role> roles = menu.getRoles();
                String[] arrRoles = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    arrRoles[i] = roles.get(i).getName();
                }
                return org.springframework.security.access.SecurityConfig.createList(arrRoles);
            }
        }
        // 未匹配上登录
        return org.springframework.security.access.SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
