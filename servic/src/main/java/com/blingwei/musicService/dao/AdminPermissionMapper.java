package com.blingwei.musicService.dao;

import com.blingwei.musicService.pojo.AdminPermission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminPermission record);

    AdminPermission selectByPrimaryKey(Integer id);

    List<AdminPermission> selectAll();

    int updateByPrimaryKey(AdminPermission record);

    List<Integer> findPermsByRoleId(Integer Id);
}
