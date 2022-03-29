package com.xjtlu.cpt202.cpt202Project.service.Impl;

import java.util.List;
import java.util.Optional;

import com.xjtlu.cpt202.cpt202Project.entity.User;
import com.xjtlu.cpt202.cpt202Project.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl {

//    public int saveUser(User u);
//    public User getUser(int id);
//    public String changeUserName(int id, String newName);
//    public String getUserName(int id);

    @Autowired
    private UserMapper userMapper;

    public int saveUser(User u){
        userMapper.addUser(u);
        return u.getId();
    }

    public User getUser(int id){
        Optional<User> u = Optional.ofNullable(userMapper.findById(id));
        return u.isPresent()? u.get() : null;
    }

    public String changeUserName(int id, String newName){
        getUser(id).setUsername(newName);
        return getUser(id).getUsername();
    }

    public String getUserName(int id){
        return getUser(id).getUsername();
    }

    public String getUserPassword(int id){
        return getUser(id).getPassword();
    }

    public List<Integer> getThumbUp(int id) {
        return userMapper.getThumbUp(id);
    }


    // public User fanNumber();
    
}
