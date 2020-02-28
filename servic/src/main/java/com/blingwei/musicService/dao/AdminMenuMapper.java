package com.blingwei.musicService.dao;

import com.blingwei.musicService.pojo.AdminMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminMenu record);

    AdminMenu selectByPrimaryKey(Integer id);

    List<AdminMenu> selectAll();

    int updateByPrimaryKey(AdminMenu record);

    List<AdminMenu> findMenuByUserId(Integer userId);

    List<Integer> findMenuByRoleId(Integer roleId);
}
