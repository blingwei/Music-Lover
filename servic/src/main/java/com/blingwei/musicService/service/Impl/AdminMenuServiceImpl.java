package com.blingwei.musicService.service.Impl;

import com.blingwei.musicService.dao.AdminMenuMapper;
import com.blingwei.musicService.dao.UserMapper;
import com.blingwei.musicService.pojo.AdminMenu;
import com.blingwei.musicService.service.AdminMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminMenuServiceImpl implements AdminMenuService {

    @Autowired
    private AdminMenuMapper adminMenuMapper;


    @Override
    public List<AdminMenu> findMenuByUserId(Integer userId) {
        return adminMenuMapper.findMenuByUserId(userId);
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleId) {
        return adminMenuMapper.findMenuByRoleId(roleId);
    }
}
