package com.blingwei.musicService.manage;

import com.blingwei.musicService.bean.requestBaen.DeleteContactRequest;
import com.blingwei.musicService.bean.requestBaen.SendMessageRequest;
import com.blingwei.musicService.bean.responseBean.ChatMessageResponse;
import com.blingwei.musicService.pojo.Message;
import com.blingwei.musicService.service.MessageService;
import com.blingwei.musicService.service.UserService;
import com.blingwei.musicService.utils.WebSocket;
import org.apache.commons.collections.list.TreeList;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class MessageManage {
    @Autowired
    private MessageService messageService;

    @Autowired
    private WebSocket webSocket;

    @Autowired
    private UserService userService;

    public List<Message> getMessages() {
        return messageService.selectByReceiveUserId(userService.getCurrentUser().getId());
    }


    @Transactional(rollbackFor = Exception.class)
    public void sendOneMessage(String userName, @NotNull Message message) {
        webSocket.sendOneMessage(userName, message.getContent());
        messageService.insert(message);
        messageService.addContact(message.getSendUserId(), message.getReceiveUserId());
    }

    public void deleteContact(@NotNull DeleteContactRequest deleteContactRequest) {
        messageService.deleteMessages(deleteContactRequest.getId(), deleteContactRequest.getContactId());
    }

    public void addContact(String charName) {
        Integer userId = userService.getCurrentUser().getId();
        Integer contactId = userService.findUserByName(charName).getId();
        messageService.addContact(userId, contactId);
    }

    public List<Message> getContacts() {
        Integer userId = userService.getCurrentUser().getId();
        return messageService.selectContactByUserId(userId);
    }

    public List<ChatMessageResponse> getChatMessages(String chatName) {
        Integer userId = userService.getCurrentUser().getId();
        Integer contactId = userService.findUserByName(chatName).getId();
        return messageService.getChatMessages(userId, contactId);
    }

    public void sendMessage(@NotNull SendMessageRequest sendMessageRequest) {
        Integer userId = userService.getCurrentUser().getId();
        Integer contactId = userService.findUserByName(sendMessageRequest.getChatName()).getId();
        Message message = new Message();
        message.setContent(sendMessageRequest.getContent());
        message.setSendUserId(userId);
        message.setReceiveUserId(contactId);
        sendOneMessage(sendMessageRequest.getChatName(), message);
    }
}
