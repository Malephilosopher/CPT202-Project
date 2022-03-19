package com.xjtlu.cpt202.cpt202Project.controller;

import com.xjtlu.cpt202.cpt202Project.entity.User;
import com.xjtlu.cpt202.cpt202Project.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    
    @GetMapping (value = "user") 
    public User getUser(@RequestParam (name = "id") int id) {
        return userService.getUser(id);
    }

    
    @GetMapping (value = "username/{id}")
    public String getUserName(@PathVariable (name = "id") int id) {
        return userService.getUserName(id);
    }

    @PostMapping (value = "change_username")
    public String changeUserName(@RequestBody int id, String newName) {
        return userService.changeUserName(id, newName);
    }

    @PostMapping (value = "new_user")
    public int newUser(@RequestBody User u) {
        return userService.saveUser(u);
    }


}
