package com.blingwei.musicService.dao;

import com.blingwei.musicService.bean.commanBean.AttentionResponse;
import com.blingwei.musicService.pojo.AdminRole;
import com.blingwei.musicService.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    List<User> findUserByNameLikely(String name);

    User findByName(String name);

    List<User> findAllUser();

    User findUserById(Integer id);

    void deleteUser(Integer id);

    void addUser(User user);

    void updateUser(User user);

    List<AttentionResponse> findAllUserByPage(@Param("message") String message, @Param("start") Integer start, @Param("size") Integer size);

    Integer findAllUserByPageNum(@Param("message") String message);

    void setUserUrl(@Param("id") Integer id, @Param("url") String url);

    AdminRole getRoleByUserId(@Param("id") Integer id);
}
