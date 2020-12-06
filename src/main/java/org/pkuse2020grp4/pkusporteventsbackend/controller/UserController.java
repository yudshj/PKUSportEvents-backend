package org.pkuse2020grp4.pkusporteventsbackend.controller;

import org.pkuse2020grp4.pkusporteventsbackend.entity.User;
import org.pkuse2020grp4.pkusporteventsbackend.repo.UserRepository;
import org.pkuse2020grp4.pkusporteventsbackend.service.UserService;
import org.pkuse2020grp4.pkusporteventsbackend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/login")
    public Result login(@RequestParam String username, @RequestParam String password){
        if(userService.checkUser(new User(username, password)) == 0)
            return Result.buildSuccessResult("Login Success", null);
        else
            return Result.buildFailResult("Login Failed", null);
    }

    @GetMapping("/user/register")
    public Result register(@RequestParam String username, @RequestParam String password, @RequestParam String repeatPassword){
        if(!password.equals(repeatPassword))
            return Result.buildFailResult("Login Failed", null);
        return userService.registerUser(new User(username, password));
    }

    /*
        This function is used for adding user when debugging
     */
    @PostMapping("/user/add")
    public int addUser(@RequestParam String username, @RequestParam String password){
        userService.registerUser(new User(username, password));
        return 0;
    }
}