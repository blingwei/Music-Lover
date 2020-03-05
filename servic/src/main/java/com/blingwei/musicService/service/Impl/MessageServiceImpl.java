package com.blingwei.musicService.service.Impl;

import com.blingwei.musicService.dao.MessageMapper;
import com.blingwei.musicService.pojo.Message;
import com.blingwei.musicService.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

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

    @Override
    public void deleteMessage(int id) {
        messageMapper.deleteByPrimaryKey(id);
    }
}
