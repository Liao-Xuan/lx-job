package com.lx.job.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: liaoxuan
 * @Date: 2020/11/14 18:43
 * @Version: 1.0
 */

@RestController
@RequestMapping("box")
public class DemoController {

    @GetMapping("demo")
    public String demo(){
        return "hello demo";
    }
}
