package com.craffic.vhr.server.util;

import com.craffic.vhr.server.domain.Hr;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class HrUtils {

    /**
     * 获取当前登录的hr对象
     * @return
     */
    public static Hr getCurrentHr(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (Hr) authentication.getPrincipal();
    }
}
