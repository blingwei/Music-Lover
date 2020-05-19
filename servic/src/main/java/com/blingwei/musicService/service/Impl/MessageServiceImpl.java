package com.blingwei.musicService.service.Impl;

import com.blingwei.musicService.bean.responseBean.ChatMessageResponse;
import com.blingwei.musicService.dao.ContactMapper;
import com.blingwei.musicService.dao.MessageMapper;
import com.blingwei.musicService.pojo.Message;
import com.blingwei.musicService.service.MessageService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {


    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private ContactMapper contactMapper;

    @Override
    public int insert(Message record) {
        return messageMapper.insert(record);
    }

    @Override
    public Message selectByPrimaryKey(Integer id) {
        return messageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Message> selectAll() {
        return messageMapper.selectAll();
    }

    @Override
    public List<Message> selectByReceiveUserId(Integer id) {
        return messageMapper.selectByReceiveUserId(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteMessages(int receiveId, int sendId) {
        messageMapper.deleteMessage(receiveId, sendId);
        deleteContact(receiveId, sendId);
    }

    public List<Message> selectContactByUserId(Integer id) {
        return messageMapper.selectContactByUserId(id);
    }

    public void deleteContact(Integer id, Integer contactId) {
        contactMapper.deleteContact(id, contactId);
    }

    public void addContact(Integer id, Integer contactId) {
        if (contactMapper.getContact(id, contactId) == null) {
            contactMapper.addContact(id, contactId);
        }
    }

    @Override
    public List<ChatMessageResponse> getChatMessages(Integer id, Integer contactId) {
        return messageMapper.getChatMessages(id, contactId);
    }
}
