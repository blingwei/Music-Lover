package com.blingwei.musicService.controller;

import com.blingwei.musicService.bean.commanBean.OperateBean;
import com.blingwei.musicService.bean.requestBaen.DeleteContactRequest;
import com.blingwei.musicService.bean.requestBaen.SendMessageRequest;
import com.blingwei.musicService.manage.MessageManage;
import com.blingwei.musicService.result.Result;
import com.blingwei.musicService.utils.ResultFactory;
import org.apache.ibatis.annotations.Param;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    private MessageManage messageManage;

    @GetMapping("getMessages")
    public Result getMessages() {
        return ResultFactory.buildSuccessResult(null, messageManage.getMessages());
    }

    @PostMapping("deleteContact")
    public Result deleteContact(@NotNull @RequestBody DeleteContactRequest deleteContactRequest) {
        messageManage.deleteContact(deleteContactRequest);
        return ResultFactory.buildSuccessResult("删除成功", null);
    }

    @PostMapping("addContact")
    public Result addContact(@NotNull @RequestBody OperateBean operateBean) {
        messageManage.addContact((String) operateBean.getObject());
        return ResultFactory.buildSuccessResult("添加成功", null);
    }


    @GetMapping("getMyContacts")
    public Result getMyContacts() {
        return ResultFactory.buildSuccessResult(null, messageManage.getContacts());
    }

    @GetMapping("getChatMessages")
    public Result getChatMessages(@Param("chatName") String chatName) {
        return ResultFactory.buildSuccessResult(null, messageManage.getChatMessages(chatName));
    }

    @PostMapping("sendMessage")
    public Result sendMessage(@NotNull @RequestBody SendMessageRequest sendMessageRequest) {
        messageManage.sendMessage(sendMessageRequest);
        return ResultFactory.buildSuccessResult("添加成功", null);
    }

}
