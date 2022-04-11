package com.xjtlu.cpt202.cpt202Project.controller;

import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.xjtlu.cpt202.cpt202Project.entity.Comment;
import com.xjtlu.cpt202.cpt202Project.entity.Result;
import com.xjtlu.cpt202.cpt202Project.entity.User;
import com.xjtlu.cpt202.cpt202Project.service.Impl.UserServiceImpl;
import com.xjtlu.cpt202.cpt202Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@RestController

public class UserController {

//    (wrong???)
    @Autowired
    private UserServiceImpl userService;



    
    @GetMapping (value = "/getPerson")
    public String getUser(@RequestParam (name = "id") int id, HttpResponse response) {
        User user = userService.getUser(id);
        Result result = Result.create(200, "get user successful", user);
        return JSON.toJSONString(result);
    }
//
//
//    @GetMapping (value = "username/{id}")
//    public String getUserName(@PathVariable (name = "id") int id) {
//        return userService.getUserName(id);
//    }
//
//    @PostMapping (value = "change_username")
//    public String changeUserName(@RequestBody int id, String newName) {
//        return userService.changeUserName(id, newName);
//    }


//login

    /**
     * 登陆
     * @param userid
     * @param password
     * @return 成功信息或失败信息
     */
    @PostMapping (value = "/login")
    public String login(@RequestParam("userid") int userid, @RequestParam("username") String username, @RequestParam("password") String password) {
        if (userService.getUserPassword(userid) != "null" && userService.getUserPassword(userid).equals(password)) {
//            right password => get the user information
            User user = userService.getUser(userid);
            Result result = Result.create(200, "get user successful", user);
            return JSON.toJSONString(result);
        } else {
//            wrong password => still login page
            User user = userService.getUser(userid);
            Result result = Result.create(300, "wrong userid or password ", user);
            return JSON.toJSONString(result);
        }
    }


    /**
     * 添加用户
     * @param user
     * @return 成功或失败信息
     */
    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) {
        userService.saveUser(user);
        Result result = Result.create(200, "add user successful", user);
        return JSON.toJSONString(result);
    }





}
