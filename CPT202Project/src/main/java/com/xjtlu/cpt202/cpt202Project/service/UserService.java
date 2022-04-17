package com.xjtlu.cpt202.cpt202Project.service;

import java.util.List;

import com.xjtlu.cpt202.cpt202Project.entity.User;

public interface UserService {

    //    save new user
    int saveUser(User u);

    //    get the information of a certain user
    User getUser(int id);

    long getUserId(String username);

//    change and get method of [username, password, email, gender, grade, major].
//   have not finished yet

    //    username
    String changeUserName(int id, String newName);
    String getUserName(int id);

    //    password
    String changeUserPassword(int id, String newPassword);
    String getUserPassword(int id);

    //    email
    String changeUserEmail(int id, String newEmail);
    String getUserEmail(int id);

    //    gender
    String changeUserGender(int id, String newGender);
    String getUserGender(int id);

    //    grade
    String changeUserGrade(int id, String newGrade);
    String getUserGrade(int id);


    List<Integer> getThumbUp(int id);

    //点赞功能
    int thumbUp(int user_id, int blog_id);

    //取消点赞
    int notThumbUp(int user_id, int blog_id);

//    List<User> getFanNumber(int id);
    
    
}
