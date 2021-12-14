package com.craffic.vhr.server.service;

import com.craffic.vhr.server.dao.HrMapper;
import com.craffic.vhr.server.dao.RoleMapper;
import com.craffic.vhr.server.domain.Hr;
import com.craffic.vhr.server.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HrService implements UserDetailsService {

    @Autowired
    private HrMapper hrMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Hr hr = hrMapper.loadUserByUsername(userName);
        if (hr == null) {
            throw new UsernameNotFoundException("用户名不存在！");
        }
        // 登录成功后，给用户setRoles
        List<Role> roles = roleMapper.getHrRolesById(hr.getId());
        hr.setRoles(roles);
        return hr;
    }
}
