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
    public Boolean login(@RequestParam String username, @RequestParam String password){
        if(userService.checkUser(username, password))
            //return Result.buildSuccessResult("Login Success", null);
            return true;
        else
            //return Result.buildFailResult("Login Failed", null);
        return false;
    }

    @GetMapping("/user/register")
    public Boolean register(@RequestParam String username, @RequestParam String password, @RequestParam String repeatPassword){
        if(!password.equals(repeatPassword))
            return false;
        userService.registerUser(new User(username, password));
            //return Result.buildSuccessResult("Login Success", null);
            return true;
            //return Result.buildFailResult("Login Failed", null);
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
