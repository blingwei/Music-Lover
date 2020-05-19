package com.blingwei.musicService.service;

import com.blingwei.musicService.bean.commanBean.AttentionResponse;
import com.blingwei.musicService.bean.responseBean.AdminUserInfoResponse;
import com.blingwei.musicService.pojo.AdminRole;
import com.blingwei.musicService.pojo.User;
import com.blingwei.musicService.pojo.UserInfo;

import java.util.List;

public interface UserService {
    User findUserByName(String name);

    List<User> findAllUser();

    User findUserById(Integer id);

    void deleteUser(Integer id);

    void addUser(User user);

    void updateUser(String name, User user);

    User getCurrentUser();

    UserInfo getCurrentUserInfo();

    UserInfo getUserInoByUserName(String userName);

    void editUser(UserInfo userInfo);

    List<AdminUserInfoResponse> getAdminUserInfos();

    List<User> findUserByNameLikely(String name);

    List<AttentionResponse> findAllUserByPage(String message, Integer start, Integer size);

    int findAllUserByPageNum(String message);

    void setUserUrl(Integer id, String url);

    AdminRole getRole(Integer id);
}
