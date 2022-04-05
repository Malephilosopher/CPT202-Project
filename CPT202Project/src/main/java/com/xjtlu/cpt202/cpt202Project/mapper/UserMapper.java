package com.xjtlu.cpt202.cpt202Project.mapper;

import com.xjtlu.cpt202.cpt202Project.entity.User;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserMapper {
//    List<User> findAllFans(int id);
    List<Integer> getThumbUp(int id);

    void addUser(User u);

    User findUserById(int userId);


}
