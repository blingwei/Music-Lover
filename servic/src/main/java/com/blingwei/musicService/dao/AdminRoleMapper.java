package com.blingwei.musicService.dao;

import com.blingwei.musicService.pojo.AdminRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminRole record);

    AdminRole selectByPrimaryKey(Integer id);

    List<AdminRole> selectAll();

    int updateByPrimaryKey(AdminRole record);

    void changeAdminRoleByUserId(@Param("userId") Integer userId,@Param("roleId") Integer roleId);

    void deleteAllRoleWithMenu(Integer roleId);

    void deleteAllRoleWithPerm(Integer roleId);

    void addRoleWithMenus(@Param("roleId") Integer roleId,@Param("list") List<Integer> menus);

    void addRoleWithPerms(@Param("roleId") Integer roleId,@Param("list") List<Integer> perms);
}
