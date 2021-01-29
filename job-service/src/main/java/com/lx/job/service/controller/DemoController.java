package com.lx.job.service.controller;

import com.lx.job.model.entity.rsp.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: liaoxuan
 * @Date: 2020/11/14 18:43
 * @Version: 1.0
 */

@RestController
@RequestMapping("box")
@Api(tags = "demo")
public class DemoController {

    @GetMapping("demo")
    public Result<String> demo(@RequestParam("id") Integer id, HttpServletRequest req) {
        return Result.success("box demo : " + id);
    }

    @GetMapping("ping")
    public Result<String> ping() {
        return Result.success("ping");
    }
}
