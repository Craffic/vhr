package com.craffic.vhr.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class employeeController {

    @GetMapping("/advanced")
    public String empAdvanced(){
        return "高级资料";
    }

    @GetMapping("/basic")
    public String empBasic(){
        return "基本资料";
    }
}