package com.blingwei.musicService.dao;

import com.blingwei.musicService.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    User findByName(String name);
    List<User> findAllUser();
    User findUserById(Integer id);
    void deleteUser(Integer id);
    void addUser(User user);
    void updateUser(User user);
}
