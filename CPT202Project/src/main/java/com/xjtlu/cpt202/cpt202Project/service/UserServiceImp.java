package com.xjtlu.cpt202.cpt202Project.service;

import java.util.Optional;

import com.xjtlu.cpt202.cpt202Project.entity.User;
import com.xjtlu.cpt202.cpt202Project.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImp {

    @Autowired
    private UserMapper userRepo;

    public int saveUser(User u){
        return userRepo.save(u).getId();
    }

    public User getUser(int id){
        Optional<User> u = userRepo.findById(id);
        return u.isPresent()? u.get() : null;
    };

    public String changeUserName(int id, String newName){
        getUser(id).setUsername(newName);
        return getUser(id).getUsername();
    };

    public String getUserName(int id){
        return getUser(id).getUsername();
    }


    // public User fanNumber();
    
}
