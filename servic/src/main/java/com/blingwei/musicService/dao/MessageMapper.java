package com.blingwei.musicService.dao;

import com.blingwei.musicService.pojo.Message;

import java.util.List;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    Message selectByPrimaryKey(Integer id);

    List<Message> selectAll();

    int updateByPrimaryKey(Message record);

    List<Message> selectByReceiveUserId(Integer id);
}