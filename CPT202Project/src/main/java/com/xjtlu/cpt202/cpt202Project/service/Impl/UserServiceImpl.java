package com.xjtlu.cpt202.cpt202Project.service.Impl;

import java.util.List;
import java.util.Optional;

import com.xjtlu.cpt202.cpt202Project.entity.Comment;
import com.xjtlu.cpt202.cpt202Project.entity.User;
import com.xjtlu.cpt202.cpt202Project.mapper.UserMapper;

import com.xjtlu.cpt202.cpt202Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int saveUser(User u) {
        return userMapper.addUser(u);
    }


    @Override
    public User getUser(int id) {
        List<Integer> idList = userMapper.getUserIdList();
        System.out.println(idList);
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

//    @Override
//    public List<Integer> getThumbUp(int id) {
//        return userMapper.getThumbUp(id);
//    }



//    @Override
//    public List<User> getFanNumber(int id) {
//        return null;
//    }


}

