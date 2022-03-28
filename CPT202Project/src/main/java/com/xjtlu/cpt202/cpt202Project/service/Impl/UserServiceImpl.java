package com.xjtlu.cpt202.cpt202Project.service.Impl;

import java.util.List;
import java.util.Optional;

import com.xjtlu.cpt202.cpt202Project.entity.User;
import com.xjtlu.cpt202.cpt202Project.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl {

    @Autowired
    private UserMapper userMapper;

    public int addUser(User u){
        return userMapper.addUser(u).getId();
    }

    public User getUser(int id){
        Optional<User> u = userMapper.findById(id);
        return u.isPresent()? u.get() : null;
    }

    public String changeUserName(int id, String newName){
        getUser(id).setUsername(newName);
        return getUser(id).getUsername();
    }

    public String getUserName(int id){
        return getUser(id).getUsername();
    }

    public List<Integer> getThumbUp(int id) {
        return userMapper.getThumbUp(id);
    }


    // public User fanNumber();
    
}
