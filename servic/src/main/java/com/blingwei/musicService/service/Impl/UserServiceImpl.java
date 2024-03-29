package com.blingwei.musicService.service.Impl;

import com.blingwei.musicService.bean.commanBean.AttentionResponse;
import com.blingwei.musicService.bean.responseBean.AdminUserInfoResponse;
import com.blingwei.musicService.dao.UserInfoMapper;
import com.blingwei.musicService.dao.UserMapper;
import com.blingwei.musicService.pojo.AdminRole;
import com.blingwei.musicService.pojo.User;
import com.blingwei.musicService.pojo.UserInfo;
import com.blingwei.musicService.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public User findUserByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public List<User> findAllUser() {
        return null;
    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    @Override
    public void deleteUser(Integer id) {

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfoMapper.insert(userInfo);
    }

    @Override
    public void updateUser(String name, User user) {

    }

    @Override
    public User getCurrentUser() {
        return findUserByName(SecurityUtils.getSubject().getPrincipal() + "");
    }

    @Override
    public UserInfo getCurrentUserInfo() {
        Integer userId = getCurrentUser().getId();
        return userInfoMapper.selectByUserId(userId);
    }

    @Override
    public UserInfo getUserInoByUserName(String userName) {
        Integer userId = findUserByName(userName).getId();
        return userInfoMapper.selectByUserId(userId);
    }

    @Override
    public void editUser(UserInfo userInfo) {
        userInfoMapper.updateByUserId(userInfo);
    }

    @Override
    public List<AdminUserInfoResponse> getAdminUserInfos() {
        return userInfoMapper.getAdminUserInfos();
    }

    @Override
    public List<User> findUserByNameLikely(String name) {
        return userMapper.findUserByNameLikely(name);
    }

    @Override
    public List<AttentionResponse> findAllUserByPage(String message, Integer start, Integer size) {
        return userMapper.findAllUserByPage(message, start, size);
    }

    @Override
    public int findAllUserByPageNum(String message) {
        return userMapper.findAllUserByPageNum(message);
    }

    @Override
    public void setUserUrl(Integer id, String url) {
        userMapper.setUserUrl(id, url);
    }

    @Override
    public AdminRole getRole(Integer id) {
        return userMapper.getRoleByUserId(id);
    }


}
