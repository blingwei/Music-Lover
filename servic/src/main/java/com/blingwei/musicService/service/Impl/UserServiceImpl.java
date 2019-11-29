package com.blingwei.musicService.service.Impl;

import com.blingwei.musicService.dao.UserMapper;
import com.blingwei.musicService.pojo.User;
import com.blingwei.musicService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public List<User> findAllUser() {
        return null;
    }

    @Override
    public void deleteUser(Integer id) {

    }

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public void updateUser(String name, User user) {

    }
}
