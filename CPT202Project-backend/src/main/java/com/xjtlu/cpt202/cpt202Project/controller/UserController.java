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


//login

    /**
     * 登陆 login
     * @param json (user)
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
        u.setLike_blog(0);
        u.setFav_blog(0);
        u.setComment_num(0);
        u.setNum_fan(0);
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
     *返回点赞数量
     * @param blog（blog_id）
     * @return code + message + data:收藏数量
     */
    @PostMapping("/thumbArticleTwo")
    public String thumbUp2(@RequestBody String blog) {
        JSONObject object = JSONObject.parseObject(blog);
        int blog_id = object.getIntValue("blog_id");
        return JSON.toJSONString(Result.create(200, "get thumb up number", userService.getThumbNum(blog_id)));
    }

    /**
     * 点赞功能
     * @param user_like（user_id + blog_id）S
     * @return code + message(thumb up or not thumb up)
     * 若无点赞记录时在user_like表中加入一条点赞记录；
     * 若点赞记录已存在则取消点赞。
     */
    @PostMapping("/thumbArticleOne")
    public String thumbup(@RequestBody String user_like) {
        JSONObject object = JSONObject.parseObject(user_like);
        int user_id = object.getIntValue("user_id");
        int blog_id = object.getIntValue("blog_id");
        int success = userService.thumbUp(user_id, blog_id);
        if (success == 1) {
            log.info(user_id + " 给id为: " + blog_id + " 的博客点赞成功");
            return JSON.toJSONString(Result.create(200, "thumb up successfully", "true"));
        } else if(success == 2){
            log.info(user_id + " 给id为: " + blog_id + " 的点赞记录已存在， 取消点赞");
            return JSON.toJSONString(Result.create(200, "cancel thumb up", "false"));
        }else{
            return JSON.toJSONString(Result.create(300, "fail to thumb up"));
        }
    }


    /**
     * 收藏行为
     * @param user_fav（user_id + blog_id）
     * @return code + message(collect or not collect)
     * 若无收藏记录时在user_like表中加入一条收藏记录；
     * 若收藏记录已存在则取消收藏。
     */
    @PostMapping("/collectOne")
    public String collectBlog(@RequestBody String user_fav) {
        JSONObject object = JSONObject.parseObject(user_fav);
        int user_id = object.getIntValue("user_id");
        int blog_id = object.getIntValue("blog_id");
        int success = userService.Collect(user_id, blog_id);
        if(success == 1) {
            log.info(user_id + " 给id为: " + blog_id + " 的博客收藏成功");
            return JSON.toJSONString(Result.create(200, "collect successfully", "true"));
        }else if(success == 2){
            log.info(user_id + " 给id为: " + blog_id + " 的博客取消收藏");
            return JSON.toJSONString(Result.create(200, "cancel collection", "false"));
        } else {
            return JSON.toJSONString(Result.create(300, "fail to collect"));
        }
    }

    /**
     *返回收藏数量
     * @param blog（blog_id）
     * @return code + message + data:收藏数量
     */
    @PostMapping("/collectTwo")
    public String getCollectNum(@RequestBody String blog) {
        JSONObject object = JSONObject.parseObject(blog);
        int blog_id = object.getIntValue("blog_id");
        return JSON.toJSONString(Result.create(200, "get the number of collect", userService.getCollectNum(blog_id)));
    }

    /**
     * 编辑用户个人信息
     * @param userInfo
     * @return code + message + data:用户完整信息
     */
    @PostMapping("editProfile")
    public String editUser(@RequestBody String userInfo){
        User u = JSON.parseObject(userInfo, User.class);
        User former = userService.getUser(u.getId());
        u.setPassword(former.getPassword());
        u.setLike_blog(former.getLike_blog());
        u.setFav_blog(former.getFav_blog());
        u.setComment_num(former.getComment_num());
        u.setNum_fan(former.getNum_fan());
        if(!u.getUsername().equals(former.getUsername())){
            if(userService.getUserId(u.getUsername()) != -1){
                return JSON.toJSONString(Result.create(300, "username already exists, change another name"));
            }
        }
        int success = userService.editUser(u);
        if(success == 1) {
            log.info("用户信息修改成功");
            return JSON.toJSONString(Result.create(200, "edit user information successfully", u));
        } else {
            log.info("用户信息修改失败");
            return JSON.toJSONString(Result.create(300, "fail to edit user information"));
        }

    }

    @PostMapping (value = "changePassword")
    public String changePassword(@RequestBody String userInfo) {
        JSONObject object = JSONObject.parseObject(userInfo);
        String newPassword = object.getString("password");
        int id = object.getIntValue("user_id");
        int success = userService.changeUserPassword(id, newPassword);
        if(success == 1) {
            log.info("用户密码修改成功");
            return JSON.toJSONString(Result.create(200, "change password successfully"));
        } else {
            log.info("用户密码修改失败");
            return JSON.toJSONString(Result.create(300, "fail to change password"));
        }
    }


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



}
