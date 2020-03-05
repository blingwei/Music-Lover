package com.blingwei.musicService.controller;

import com.blingwei.musicService.bean.commanBean.OperateBean;
import com.blingwei.musicService.manage.MessageManage;
import com.blingwei.musicService.result.Result;
import com.blingwei.musicService.utils.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    private MessageManage messageManage;

    @GetMapping("getMessages")
    public Result getMessages(){
        return ResultFactory.buildSuccessResult(null, messageManage.getMessages());
    }

    @PostMapping("deleteMessage")
    public Result deleteMessage(@RequestBody OperateBean operateBean){
        messageManage.deleteMessage(operateBean.getId());
        return ResultFactory.buildSuccessResult(null, null);
    }


}
