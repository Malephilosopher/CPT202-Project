package com.xjtlu.cpt202.cpt202Project.mapper;

import com.xjtlu.cpt202.cpt202Project.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    //  public List<User> u = 
}
