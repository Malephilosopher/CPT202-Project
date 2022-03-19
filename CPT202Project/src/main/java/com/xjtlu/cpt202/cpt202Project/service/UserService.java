package com.xjtlu.cpt202.cpt202Project.service;

import java.util.List;

import com.xjtlu.cpt202.cpt202Project.entity.User;

public interface UserService {

    public int saveUser(User u);
    public User getUser(int id);
    public String changeUserName(int id, String newName);
    public String getUserName(int id);
    // [password, email, gender, grade, major] also have change and get method(int id).

    public List<User> getFanNumber(int id);
    
    
}
