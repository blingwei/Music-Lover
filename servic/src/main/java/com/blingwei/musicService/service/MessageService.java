package com.blingwei.musicService.service;

import com.blingwei.musicService.pojo.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {
    int insert(Message record);

    Message selectByPrimaryKey(Integer id);

    List<Message> selectAll();

    List<Message> selectByReceiveUserId(Integer id);

    void deleteMessage(int id);
}
