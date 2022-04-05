package com.xjtlu.cpt202.cpt202Project.service;

import java.util.List;

import com.xjtlu.cpt202.cpt202Project.entity.User;
import org.springframework.stereotype.Service;


public interface UserService {
//    save new user
    public int saveUser(User u);
//    get the information of a certain user
    public User getUser(int id);

//    change and get method of [username, password, email, gender, grade, major].
//   have not finished yet

//    username
    public String changeUserName(int id, String newName);
    public String getUserName(int id);

//    password
    public String changeUserPassword(int id, String newPassword);
    public String getUserPassword(int id);

//    email
    public String changeUserEmail(int id, String newEmail);
    public String getUserEmail(int id);

//    gender
    public boolean changeUserGender(int id, boolean newGender);
    public boolean getUserGender(int id);

//    grade
    public int changeUserGrade(int id, int newGrade);
    public int getUserGrade(int id);






    public List<User> getFanNumber(int id);
    
    
}
