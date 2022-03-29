package com.xjtlu.cpt202.cpt202Project.controller;

import com.alibaba.fastjson.JSON;
import com.xjtlu.cpt202.cpt202Project.entity.Comment;
import com.xjtlu.cpt202.cpt202Project.entity.Result;
import com.xjtlu.cpt202.cpt202Project.entity.User;
import com.xjtlu.cpt202.cpt202Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

//    (wrong???)
    @Autowired
    private UserService userService;

    
//    @GetMapping (value = "user")
//    public String getUser(@RequestParam (name = "id") int id) {
//        User user = userService.getUser(id);
//        Result result = Result.create(200, "get user successful", user);
//        return JSON.toJSONString(result);
//    }
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

    @PostMapping (value = "/login")
    public User login(@RequestBody int userid, String password) {
        if (userService.getUserPassword(userid).equals(password)) {
//            right password => get the user information
            return userService.getUser(userid);
        } else {
//            wrong password => still login page
//            ???not sure the return when the password is wrong
            return null;
        }
    }


    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) {
        userService.saveUser(user);
        Result result = Result.create(200, "add user successful", user);
        return JSON.toJSONString(result);
    }




}
