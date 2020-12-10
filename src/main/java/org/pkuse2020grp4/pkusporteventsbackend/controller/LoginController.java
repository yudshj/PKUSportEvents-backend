package org.pkuse2020grp4.pkusporteventsbackend.controller;

import org.pkuse2020grp4.pkusporteventsbackend.configuation.JwtConfig;
import org.pkuse2020grp4.pkusporteventsbackend.entity.User;
import org.pkuse2020grp4.pkusporteventsbackend.exception.UserNotFoundException;
import org.pkuse2020grp4.pkusporteventsbackend.service.UserService;
import org.pkuse2020grp4.pkusporteventsbackend.utils.JwtUtils;
import org.pkuse2020grp4.pkusporteventsbackend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtConfig jwtConfig;

    @PostMapping("/api/login")
    public Result login(@RequestBody User user) throws Exception {
        int status = userService.getUserId(user);
        user.setUserId(status);
        String token = JwtUtils.sign(user, jwtConfig);
        return Result.buildSuccessResult("登录成功", token);
    }

    @PostMapping("/api/register")
    public Result register(@RequestBody User user){
        int status = userService.registerUser(user);
        return switch (status) {
            case 0 -> Result.buildSuccessResult("注册成功");
            case 1 -> Result.buildFailResult("用户已存在");
            case 2 -> Result.buildFailResult("用户名不符合格式或密码过短");
            default -> Result.buildFailResult("未知错误");
        };
    }

    @PostMapping("/api/salt")
    public String salt(@RequestBody String username){
        return username;
    }
}
