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
}
