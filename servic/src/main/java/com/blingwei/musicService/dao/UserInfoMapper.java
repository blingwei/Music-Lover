package com.blingwei.musicService.dao;

import com.blingwei.musicService.pojo.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    List<UserInfo> selectAll();

    int updateByPrimaryKey(UserInfo record);

    UserInfo selectByUserId(Integer userId);

    int updateByUserId(UserInfo record);
}