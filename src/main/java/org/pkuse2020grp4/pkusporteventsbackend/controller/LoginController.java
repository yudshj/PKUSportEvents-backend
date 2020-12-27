package org.pkuse2020grp4.pkusporteventsbackend.controller;

import org.pkuse2020grp4.pkusporteventsbackend.configuation.JwtConfig;
import org.pkuse2020grp4.pkusporteventsbackend.dto.UserDTO;
import org.pkuse2020grp4.pkusporteventsbackend.entity.User;
import org.pkuse2020grp4.pkusporteventsbackend.perm.perm;
import org.pkuse2020grp4.pkusporteventsbackend.service.UserService;
import org.pkuse2020grp4.pkusporteventsbackend.utils.JwtUtils;
import org.pkuse2020grp4.pkusporteventsbackend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtConfig jwtConfig;

    @PostMapping("/api/login")
    public Result login(@RequestBody @Valid UserDTO userDTO) throws Exception {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        int status = userService.getUserId(user);
        user.setUserId(status);
        String token = JwtUtils.sign(user, jwtConfig);
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("uid", status);
        return Result.buildSuccessResult("登录成功", data);
    }

    @PostMapping("/api/register")
    public Result register(@RequestBody @Valid UserDTO userDTO){
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setPermission(perm.DEFAULT);
        int status = userService.registerUser(user);
        switch (status) {
            case 0:
                return Result.buildSuccessResult("注册成功");
            case 1:
                return Result.buildFailResult("用户已存在");
            case 2:
                return Result.buildFailResult("用户名不符合格式或密码过短");
            default:
                return Result.buildFailResult("未知错误");
        }
    }

    // @PostMapping("/api/salt")
    // public String salt(@RequestBody String username){
    //     return username;
    // }
}
