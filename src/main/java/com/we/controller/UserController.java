package com.we.controller;

import com.we.entity.user.param.UserCreateParam;
import com.we.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(value="/user")
public class UserController {
    @Autowired
    UserService userService;



    @RequestMapping(value="/create",method = RequestMethod.POST)
    public String CreateUser(UserCreateParam param){
        return userService.createUser(param);
    }


    @RequestMapping(value="/sync",method = RequestMethod.POST)
    public String syncUser(String json){

        return "";
    }

}
