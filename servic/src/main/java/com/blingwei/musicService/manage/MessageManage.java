package com.blingwei.musicService.manage;

import com.blingwei.musicService.pojo.Message;
import com.blingwei.musicService.service.MessageService;
import com.blingwei.musicService.service.UserService;
import com.blingwei.musicService.utils.WebSocket;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageManage {
    @Autowired
    private MessageService messageService;

    @Autowired
    private WebSocket webSocket;

    @Autowired
    private UserService userService;

    public List<Message> getMessages(){
        return messageService.selectByReceiveUserId(userService.getCurrentUser().getId());
    }


    public void sendOneMessage(String userName, @NotNull Message message) {
        webSocket.sendOneMessage(userName, message.getContent());
        messageService.insert(message);
    }

    public void deleteMessage(int id) {
        messageService.deleteMessage(id);
    }
}
