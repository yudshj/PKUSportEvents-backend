package org.pkuse2020grp4.pkusporteventsbackend.controller;

import org.pkuse2020grp4.pkusporteventsbackend.entity.User;
import org.pkuse2020grp4.pkusporteventsbackend.repo.UserRepository;
import org.pkuse2020grp4.pkusporteventsbackend.service.UserService;
import org.pkuse2020grp4.pkusporteventsbackend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/api/login")
    public Result login(@RequestBody User user){
        int status = userService.checkUser(user);
        switch (status) {
            case 0:
                return Result.buildSuccessResult("登录成功");
            case 1:
                return Result.buildFailResult(String.format("用户 \"%s\" 不存在", user.getUsername()));
            case 2:
                return Result.buildFailResult("密码有误");
        }
        return Result.buildFailResult("未知错误");
    }

    @PostMapping("/api/register")
    public Result register(@RequestBody User user){
        int status = userService.registerUser(user);
        switch (status) {
            case 0:
                return Result.buildSuccessResult("注册成功");
            case 1:
                return Result.buildFailResult("用户已存在");
            case 2:
                return Result.buildFailResult("用户名不符合格式或密码过短");
        }
        return Result.buildFailResult("未知错误");
    }

    @PostMapping("/api/salt")
    public String salt(@RequestBody String username){
        return username;
    }
}
