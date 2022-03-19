package com.cpt202.userdemo.Service;

import java.util.Optional;

import com.cpt202.userdemo.Model.User;
import com.cpt202.userdemo.Repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImp {

    @Autowired
    private UserRepo userRepo;

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
