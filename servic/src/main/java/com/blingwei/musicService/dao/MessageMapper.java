package com.blingwei.musicService.dao;

import com.blingwei.musicService.bean.responseBean.ChatMessageResponse;
import com.blingwei.musicService.pojo.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    Message selectByPrimaryKey(Integer id);

    List<Message> selectAll();

    int updateByPrimaryKey(Message record);

    List<Message> selectByReceiveUserId(Integer id);

    void deleteMessage(@Param("receiveId") int receiveId, @Param("sendId") int sendId);

    List<Message> selectContactByUserId(Integer id);

    List<ChatMessageResponse> getChatMessages(@Param("id") Integer id, @Param("contactId") Integer contactId);

}