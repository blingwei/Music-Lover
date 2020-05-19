package com.blingwei.musicService.service.Impl;

import com.blingwei.musicService.bean.responseBean.AdminRolesResponse;
import com.blingwei.musicService.dao.AdminRoleMapper;
import com.blingwei.musicService.pojo.AdminRole;
import com.blingwei.musicService.service.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AdminRoleServiceImpl implements AdminRoleService {

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Override
    public List<AdminRole> getAllAdminRole() {
        return adminRoleMapper.selectAll();
    }

    @Override
    public void changeAdminRoleByUserId(Integer userId, Integer roleId) {
        adminRoleMapper.changeAdminRoleByUserId(userId, roleId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void editRole(AdminRole adminRole, List<Integer> menus, List<Integer> perms) {
        updateRoleInfo(adminRole);
        updateRoleWithMenus(adminRole.getId(), menus);
        updateRoleWithPerms(adminRole.getId(), perms);
    }

    @Override
    public int addRole(AdminRole adminRole) {
        return adminRoleMapper.insert(adminRole);
    }

    public void updateRoleInfo(AdminRole adminRole) {
        adminRoleMapper.updateByPrimaryKey(adminRole);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateRoleWithMenus(Integer roleId, List<Integer> menus) {
        adminRoleMapper.deleteAllRoleWithMenu(roleId);
        if (!CollectionUtils.isEmpty(menus)) {
            adminRoleMapper.addRoleWithMenus(roleId, menus);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateRoleWithPerms(Integer roleId, List<Integer> perms) {
        adminRoleMapper.deleteAllRoleWithPerm(roleId);
        if (!CollectionUtils.isEmpty(perms)) {
            adminRoleMapper.addRoleWithPerms(roleId, perms);
        }

    }


}
