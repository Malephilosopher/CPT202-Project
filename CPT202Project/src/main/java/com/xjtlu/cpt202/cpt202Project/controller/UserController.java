package com.xjtlu.cpt202.cpt202Project.controller;

import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
import java.util.*;

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
     * @param json :username + password
     * @return 成功信息或失败信息
     */
    @PostMapping (value = "/login")
    public String login(@RequestBody String json) {
        User u = JSON.parseObject(json, User.class);
        String username = u.getUsername();
        String password = u.getPassword();
        log.info("username: " + username);
        log.info("password: " + password);

        int userid = (int)userService.getUserId(username);
        if(userid == -1){
            Result result = Result.create(300, "user do not exist");
            return JSON.toJSONString(result);
        }
        if (!Objects.equals(userService.getUserPassword(userid), "null") && userService.getUserPassword(userid).equals(password)) {
//            right password => get the user information
            String token = "";
            try {
                token = JwtUtil.createToken(String.valueOf(userid), username, "user", audience);
                log.info("### 登录成功, token={} ###", token);
            }catch (UserException e) {
                e.printStackTrace();
                Result res = Result.create(300, "permission signature error");
                return JSON.toJSONString(res);
            }

            User user = userService.getUser(userid);
            Result result = Result.success("get user successful", user, token);
            return JSON.toJSONString(result);
        } else {
//            wrong password => still login page
            User user = userService.getUser(userid);
            Result result = Result.create(300, "wrong userid or password ");
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
        User u = JSON.parseObject(user, User.class);
        log.info("user to be added: " + u.getUsername());
        if(userService.getUserId(u.getUsername()) != -1){
            return JSON.toJSONString(Result.create(300, "username already exists, change another name"));
        }
        int success = userService.saveUser(u);
        if (success == 1) {
            return JSON.toJSONString(Result.create(200, "add user successfully", u));
        } else {
            return JSON.toJSONString(Result.create(300, "failed to add user "));
        }

    }

    /**
     * 获取用户点赞文章
     * @param id
     * @return
     */
    @GetMapping (value = "/getLikeBlogs")
    public String like(@RequestParam (name = "userid") int id) {
        List<Integer> userLike = userService.getThumbUp(id);
            return JSON.toJSONString(Result.create(200, "get user thumb up blog successfully", userLike));
        }


    @PostMapping("/like")
    public String like(@RequestBody String user_like) {
        JSONObject object = JSONObject.parseObject(user_like);
        int user_id = object.getIntValue("user_id");
        int blog_id = object.getIntValue("blog_id");
        int success = userService.thumbUp(user_id, blog_id);
        List<Integer> userLike = userService.getThumbUp(user_id);
        if (userLike.contains(blog_id)) {
            userService.notThumbUp(user_id, blog_id);
            return JSON.toJSONString(Result.create(200, "not thumb up"));
        } else {
            if (success == 1) {
                return JSON.toJSONString(Result.create(200, "thumb up successfully"));
            } else {
                return JSON.toJSONString(Result.create(300, "failed to thumb up"));
            }
        }
    }



}
