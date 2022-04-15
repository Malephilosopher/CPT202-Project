package com.xjtlu.cpt202.cpt202Project.controller;

import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.xjtlu.cpt202.cpt202Project.Exception.UserException;
import com.xjtlu.cpt202.cpt202Project.Utils.JwtUtil;
import com.xjtlu.cpt202.cpt202Project.entity.Audience;
import com.xjtlu.cpt202.cpt202Project.entity.Comment;
import com.xjtlu.cpt202.cpt202Project.entity.Result;
import com.xjtlu.cpt202.cpt202Project.entity.User;
import com.xjtlu.cpt202.cpt202Project.service.Impl.UserServiceImpl;
import com.xjtlu.cpt202.cpt202Project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

//    (wrong???)
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private Audience audience;

    
    @GetMapping (value = "/getPerson")
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
     * @param password
     * @return 成功信息或失败信息
     */
    @PostMapping (value = "/login")
    public String login(@RequestBody String json) {
        User u = JSON.parseObject(json, User.class);
        String username = u.getUsername();
        String password = u.getPassword();
        log.info("username: " + username);
        log.info("password: " + password);

        long userid = userService.getUserId(username);
        if(userid == -1){
            Result result = Result.create(300, "user do not exist");
            return JSON.toJSONString(result);
        }
        int uid = (int)userid;
        if (!Objects.equals(userService.getUserPassword(uid), "null") && userService.getUserPassword(uid).equals(password)) {
//            right password => get the user information
            String token = "";
            try {
                token = JwtUtil.createToken(String.valueOf(uid), username, "user", audience);
                log.info("### 登录成功, token={} ###", token);
            }catch (UserException e) {
                e.printStackTrace();
                Result res = Result.create(300, "permission signature error");
                return JSON.toJSONString(res);
            }

            User user = userService.getUser(uid);
            Result result = Result.success("get user successful", user, token);
            return JSON.toJSONString(result);
        } else {
//            wrong password => still login page
            User user = userService.getUser(uid);
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
    public String addUser(@RequestBody String user) {
        System.out.println(user);
        User u = JSON.parseObject(user, User.class);
        System.out.println(u.getUsername());
        int id = userService.saveUser(u);
        User newUser = userService.getUser(id);
        if(newUser == null ){
            return JSON.toJSONString(Result.create(300, "failed to add user "));
        } else {
            return JSON.toJSONString(Result.create(200, "add user successfully", newUser));
        }
    }





}
