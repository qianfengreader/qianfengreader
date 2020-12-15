package com.qf.controller;

import com.qf.common.BaseResp;
import com.qf.pojo.User;
import com.qf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/sendMail",method = RequestMethod.POST)
    public BaseResp sendMail(@RequestBody Map map){
        return userService.sendMail(map.get("email").toString());
    }
    @RequestMapping(value = "/registy",method = RequestMethod.POST)
    public BaseResp registy(@RequestBody User user){
        return userService.registy(user);
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public BaseResp login(@RequestBody User user){
        return userService.login(user);
    }
}
