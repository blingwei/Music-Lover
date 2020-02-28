package com.blingwei.musicService.bean.responseBean;

import com.blingwei.musicService.pojo.AdminMenu;
import com.blingwei.musicService.pojo.AdminPermission;
import com.blingwei.musicService.pojo.AdminRole;

import java.util.List;

public class AdminRolesResponse {
    private AdminRole adminRole;
    private List<Integer> menus;
    private List<Integer> perms;

    public AdminRole getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(AdminRole adminRole) {
        this.adminRole = adminRole;
    }

    public List<Integer> getMenus() {
        return menus;
    }

    public void setMenus(List<Integer> menus) {
        this.menus = menus;
    }

    public List<Integer> getPerms() {
        return perms;
    }

    public void setPerms(List<Integer> perms) {
        this.perms = perms;
    }
}
