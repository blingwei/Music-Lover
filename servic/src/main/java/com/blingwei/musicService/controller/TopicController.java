package com.blingwei.musicService.controller;

import com.blingwei.musicService.bean.requestBaen.ContributeForTopicRequest;
import com.blingwei.musicService.bean.responseBean.TopicInfoResponse;
import com.blingwei.musicService.manage.TopicManage;
import com.blingwei.musicService.result.Result;
import com.blingwei.musicService.service.TopicService;
import com.blingwei.musicService.utils.ResultFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TopicController {

    @Autowired
    private TopicManage topicManage;


    @GetMapping("getTopicInfo")
    public Result getTopicInfo(@Param("topicId") Integer topicId){
        if(topicId!=null){
            TopicInfoResponse topicInfoResponse = topicManage.getTopicInfo(topicId);
            return ResultFactory.buildSuccessResult("", topicInfoResponse);
        }
        return ResultFactory.buildFailResult("发生未知错误");
    }

    @PostMapping("contributeForTopic")
    public Result contributeForTopic(@RequestBody ContributeForTopicRequest contributeForTopicRequest){
        try{
            topicManage.contributeForTopic(contributeForTopicRequest);
            return ResultFactory.buildSuccessResult("", null);
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult(e.getMessage());
        }


    }



}
