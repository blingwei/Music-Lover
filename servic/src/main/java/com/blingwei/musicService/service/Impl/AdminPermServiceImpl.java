package com.blingwei.musicService.service.Impl;

import com.blingwei.musicService.dao.AdminPermissionMapper;
import com.blingwei.musicService.pojo.AdminPermission;
import com.blingwei.musicService.service.AdminPermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminPermServiceImpl implements AdminPermService {
    @Autowired
    private AdminPermissionMapper adminPermissionMapper;

    @Override
    public List<AdminPermission> getAllPerm() {
        return adminPermissionMapper.selectAll();
    }

    @Override
    public List<Integer> findPermsByRoleId(Integer id) {
        return adminPermissionMapper.findPermsByRoleId(id);
    }

    @Override
    public List<AdminPermission> findPermsByUserName(String username) {
        return adminPermissionMapper.findPermsByUserName(username);
    }

    public boolean needFilter(String requestAPI) {
        List<AdminPermission> ps = getAllPerm();
        for (AdminPermission p: ps) {
            // 这里我们进行前缀匹配，拥有父权限就拥有所有子权限
            if (requestAPI.startsWith(p.getUrl())) {
                return true;
            }
        }
        return false;
    }
}
