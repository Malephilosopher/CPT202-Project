package com.xjtlu.cpt202.cpt202Project.controller;

import com.alibaba.fastjson.JSON;
import com.xjtlu.cpt202.cpt202Project.entity.Comment;
import com.xjtlu.cpt202.cpt202Project.entity.Result;
import com.xjtlu.cpt202.cpt202Project.entity.User;
import com.xjtlu.cpt202.cpt202Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

@RestController
public class UserController {

//    (wrong???)
    @Autowired
    private static UserService userService;

    
    @GetMapping (value = "getPerson")
    public String getUser(@RequestParam (name = "id") int id) {
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
    public String login(@RequestBody int userid, String password) {
        if (userService.getUserPassword(userid).equals(password)) {
//            right password => get the user information
            return JSON.toJSONString(userService.getUser(userid));
        } else {
//            wrong password => still login page
//            return 300 when the password is wrong
            return JSON.toJSONString(Result.fail("wrong password!"));
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
