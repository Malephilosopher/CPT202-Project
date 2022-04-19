package com.xjtlu.cpt202.cpt202Project.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xjtlu.cpt202.cpt202Project.exceptions.UserException;
import com.xjtlu.cpt202.cpt202Project.util.JwtUtil;
import com.xjtlu.cpt202.cpt202Project.entity.Audience;
import com.xjtlu.cpt202.cpt202Project.entity.Result;
import com.xjtlu.cpt202.cpt202Project.entity.User;
import com.xjtlu.cpt202.cpt202Project.service.Impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

//    (wrong???)
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private Audience audience;

    /**
     * 个人详情页获取用户信息+点赞/收藏/创作过的文章
     * @param id 用户id
     * @return 用户信息+点赞/收藏/创作过的文章
     *
     */
    @GetMapping (value = "/getPerson")
    public String getUser(@RequestParam (name = "author_id") int id) {
        User user = userService.getUser(id);
        //点赞过的文章
        List<Integer> userLike = userService.getThumbUpId(id);
        //收藏的文章
        List<Integer> userCollect = userService.getCollectId(id);
        //创作的文章
        List<Integer> userCreate = userService.getCreateId(id);

        Result result = Result.create(200, "get user successful", List.of(user, userLike, userCollect, userCreate));
        log.info("获取" + user.getUsername() + "用户信息成功");
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
     * 登陆 login
     * @param json
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
     * 注册 registration
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
     * @return 用户点赞过的所有文章id
     */
    @GetMapping (value = "/getLikeBlogs")
    public String like(@RequestParam (name = "userid") int id) {
        List<Integer> userLike = userService.getThumbUpId(id);
        log.info("获取user (" + id +") 点赞过的文章");
        return JSON.toJSONString(Result.create(200, "get user thumb up blog successfully", userLike));
    }










}
