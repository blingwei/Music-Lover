package com.blingwei.musicService.service;

import com.blingwei.musicService.pojo.User;

import java.util.List;

public interface UserService {
    User findUserByName(String name);
    List<User> findAllUser();
    User findUserById(Integer id);
    void deleteUser(Integer id);
    void addUser(User user);
    void updateUser(String name, User user);
}
