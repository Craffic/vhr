package com.craffic.vhr.web.exception;


import com.craffic.vhr.model.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public RespBean sqlException(SQLException e){
        if (e instanceof SQLException){
            System.out.println(e);
            return RespBean.error("数据库错误，操作失败！");
        }
        return RespBean.error("其他数据库异常，操作失败！");
    }


}
