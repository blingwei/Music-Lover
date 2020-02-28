package com.blingwei.musicService.service;

import com.blingwei.musicService.pojo.AdminMenu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminMenuService {
    List<AdminMenu> findMenuByUserId(Integer userId);

    List<Integer> findMenuByRoleId(Integer roleId);
}
