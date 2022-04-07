package com.xjtlu.cpt202.cpt202Project.service.Impl;

import java.util.List;
import java.util.Optional;

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
        userMapper.addUser(u);
        return u.getId();
    }

    @Override
    public User getUser(int id) {
        Optional<User> u = Optional.ofNullable(userMapper.findUserById(id));
        return u.isPresent()? u.get() : null;
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
    public boolean changeUserGender(int id, boolean newGender) {
        User u = userMapper.findUserById(id);
        u.setGender(newGender);
        userMapper.updateUser(u);
        return u.isGender();
    }

    @Override
    public boolean getUserGender(int id) {
        return getUser(id).isGender();
    }

    @Override
    public int changeUserGrade(int id, int newGrade) {
        User u = userMapper.findUserById(id);
        u.setGrade(newGrade);
        userMapper.updateUser(u);
        return u.getGrade();
    }

    @Override
    public int getUserGrade(int id) {
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

