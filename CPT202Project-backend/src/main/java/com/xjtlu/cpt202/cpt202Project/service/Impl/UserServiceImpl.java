package com.xjtlu.cpt202.cpt202Project.service.Impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import cn.hutool.core.util.BooleanUtil;
import com.xjtlu.cpt202.cpt202Project.entity.Comment;
import com.xjtlu.cpt202.cpt202Project.entity.User;
import com.xjtlu.cpt202.cpt202Project.mapper.BlogMapper;
import com.xjtlu.cpt202.cpt202Project.mapper.UserMapper;

import com.xjtlu.cpt202.cpt202Project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public int saveUser(User u) {
         return userMapper.addUser(u);
    }


    @Override
    public User getUser(int id) {
        List<Integer> idList = userMapper.getUserIdList();
        if (idList.contains(id)){
            return userMapper.findUserById(id);
        } else {
            return null;
        }

    }

    @Override
    public long getUserId(String username) {
        Object o = userMapper.getUserId(username);
        if(o == null){
            return -1;
        }else {
            return (long) o;
        }
    }

    @Override
    public List<Integer> getThumbUpId(int id) {
        return userMapper.getThumbUpId(id);
    }

    @Override
    public int thumbUp(int user_id, int blog_id) {
        //1. 获取当前登陆用户
        //2. 判断当前用户是否点过赞
        String key = "blog:liked:" + blog_id;
        boolean isMember = redisTemplate.opsForSet().isMember(key, String.valueOf(user_id));
        if(BooleanUtil.isFalse(isMember)){
            //3. 如果未点赞，数据库点赞数+1
            int success = userMapper.addLikeNum(blog_id);
            int su2 = userMapper.addLike(user_id, blog_id);
            if(success > 0&& su2 > 0){
                //保存用户到redis的set集合
                redisTemplate.opsForSet().add(key, String.valueOf(user_id));
                return 1;
            }else {
                return 0;
            }
        }else {
            //如果已点赞，取消点赞，数据库点赞数-1
            int succ = userMapper.reduceLikeNum(blog_id);
            int succ2 = userMapper.cancelLike(user_id, blog_id);
            //将用户从redis的set集合移除
            if(succ > 0 && succ2 > 0){
                redisTemplate.opsForSet().remove(key, String.valueOf(user_id));
                return 2;
            }else {
                return 0;
            }

        }
    }

    @Override
    public List<Integer> getCollectId(int user_id){
        return userMapper.getCollectId(user_id);
    }

    @Override
    public int Collect(int user_id, int blog_id) {
        //1. 获取当前登陆用户
        //2. 判断当前用户是否收藏
        String key = "blog:collected:" + blog_id;
        boolean isMember = redisTemplate.opsForSet().isMember(key, String.valueOf(user_id));
        if(BooleanUtil.isFalse(isMember)){
            //3. 如果未收藏，数据库收藏数+1
            int success = userMapper.addCollectNum(blog_id);
            int su2 = userMapper.addCollect(user_id, blog_id);
            if(success > 0 && su2 > 0){
                //保存用户到redis的set集合
                redisTemplate.opsForSet().add(key, String.valueOf(user_id));
                return 1;
            }else {
                return 0;
            }
        }else {
            //如果已收藏，取消收藏，数据库收藏数-1
            int succ = userMapper.reduceCollectNum(blog_id);
            int succ2 = userMapper.cancelCollect(user_id, blog_id);
            //将用户从redis的set集合移除
            if(succ > 0 && succ2 > 0){
                redisTemplate.opsForSet().remove(key, String.valueOf(user_id));
                return 2;
            }else {
                return 0;
            }

        }
    }


    @Override
    public Integer getCollectNum(int blog_id) {
        return userMapper.getCollectNum(blog_id);
    }

    @Override
    public String changeUserName(int id, String newName) {
        User u = userMapper.findUserById(id);
        u.setUsername(newName);
        userMapper.updateUser(u);
        return u.getUsername();
    }


    @Override
    public String getUserName(int id) {
        return getUser(id).getUsername();
    }


    //    to be finished
    @Override
    public String changeUserPassword(int id, String newPassword) {
        User u = userMapper.findUserById(id);
        u.setPassword(newPassword);
        userMapper.updateUser(u);
        return u.getPassword();
    }

    @Override
    public String getUserPassword(int id){
        if(getUser(id) == null){
            return "null";
        }
        return getUser(id).getPassword();
    }

    @Override
    public String changeUserEmail(int id, String newEmail) {
        User u = userMapper.findUserById(id);
        u.setEmail(newEmail);
        userMapper.updateUser(u);
        return u.getEmail();
    }

    @Override
    public String getUserEmail(int id) {
        return getUser(id).getEmail();
    }

    @Override
    public String changeUserGender(int id, String newGender) {
        User u = userMapper.findUserById(id);
        u.setGender(newGender);
        userMapper.updateUser(u);
        return u.getGender();
    }

    @Override
    public String getUserGender(int id) {
        return getUser(id).getGender();
    }

    @Override
    public String changeUserGrade(int id, String newGrade) {
        User u = userMapper.findUserById(id);
        u.setGrade(newGrade);
        userMapper.updateUser(u);
        return u.getGrade();
    }

    @Override
    public String getUserGrade(int id) {
        return getUser(id).getGrade();
    }

    @Override
    public List<Integer> getCreateId(int id) {
        return userMapper.getCreateId(id);
    }

    @Override
    public Integer getThumbNum(int blog_id) {
        return userMapper.getThumbNum(blog_id);
    }

}

