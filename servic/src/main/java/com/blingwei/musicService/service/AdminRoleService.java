package com.blingwei.musicService.service;

import com.blingwei.musicService.bean.responseBean.AdminRolesResponse;
import com.blingwei.musicService.pojo.AdminRole;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminRoleService {
    List<AdminRole> getAllAdminRole();

    void changeAdminRoleByUserId(Integer userId, Integer roleId);

    void editRole(AdminRole adminRole, List<Integer> menus, List<Integer> perms);

    int addRole(AdminRole adminRole);
}
