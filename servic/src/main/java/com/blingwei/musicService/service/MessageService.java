package com.blingwei.musicService.service;

import com.blingwei.musicService.bean.responseBean.ChatMessageResponse;
import com.blingwei.musicService.pojo.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {
    int insert(Message record);

    Message selectByPrimaryKey(Integer id);

    List<Message> selectAll();

    List<Message> selectByReceiveUserId(Integer id);

    void deleteMessages(int receiveId, int sendId);

    List<Message> selectContactByUserId(Integer id);

    void deleteContact(Integer id, Integer contactId);

    void addContact(Integer id, Integer contactId);

    List<ChatMessageResponse> getChatMessages(Integer id, Integer contactId);
}
