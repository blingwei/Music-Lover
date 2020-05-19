package com.blingwei.musicService.controller;

import com.blingwei.musicService.bean.commanBean.AttentionResponse;
import com.blingwei.musicService.bean.requestBaen.AddTopicRequest;
import com.blingwei.musicService.bean.responseBean.EssayWithSongCardResponse;
import com.blingwei.musicService.bean.responseBean.ConditionResponse;
import com.blingwei.musicService.bean.responseBean.PublishInfoResponse;
import com.blingwei.musicService.enums.TypeEnum;
import com.blingwei.musicService.manage.PersonalManage;
import com.blingwei.musicService.manage.UserCollectManage;
import com.blingwei.musicService.result.Result;
import com.blingwei.musicService.service.UserService;
import com.blingwei.musicService.utils.ResultFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 86187
 */
@RestController
@RequestMapping("/api")
public class PersonalController {
    @Autowired
    private PersonalManage personalManage;

    @Autowired
    private UserCollectManage userCollectManage;

    @Autowired
    private UserService userService;

    @GetMapping("/getCollectInfos")
    public Result getCollectInfos(@Param("userName") String userName) {
        List<EssayWithSongCardResponse> res = personalManage.getCollects(userName);
        return ResultFactory.buildSuccessResult("", res);
    }

    @GetMapping("collectAttention")
    public Result collectAttention(@Param("userName") String userName) {
        Integer matterId = userService.findUserByName(userName).getId();
        int collectNum = userCollectManage.collect(matterId, TypeEnum.USER);
        return ResultFactory.buildSuccessResult(null, collectNum);
    }

    @GetMapping("cancelCollectAttention")
    public Result cancelCollectAttention(@Param("userName") String userName) {
        Integer matterId = userService.findUserByName(userName).getId();
        int collectNum = userCollectManage.cancelCollect(matterId, TypeEnum.USER);
        return ResultFactory.buildSuccessResult(null, collectNum);
    }

    @GetMapping("collectTopic")
    public Result collectTopic(@Param("id") Integer id) {
        int collectNum = userCollectManage.collect(id, TypeEnum.TOPIC);
        return ResultFactory.buildSuccessResult(null, collectNum);
    }

    @GetMapping("cancelCollectTopic")
    public Result cancelCollectTopic(@Param("id") Integer id) {
        int collectNum = userCollectManage.cancelCollect(id, TypeEnum.TOPIC);
        return ResultFactory.buildSuccessResult(null, collectNum);
    }

    @GetMapping("getMyAttention")
    public Result getMyAttention(@Param("userName") String userName) {
        List<AttentionResponse> res = userCollectManage.getMyAttention(userName);
        return ResultFactory.buildSuccessResult(null, res);
    }

    @GetMapping("getAttentionMe")
    public Result getAttentionMe(@Param("userName") String userName) {
        List<AttentionResponse> res = userCollectManage.getAttentionMe(userName);
        return ResultFactory.buildSuccessResult(null, res);
    }

    @GetMapping("getAttentionTopic")
    public Result getAttentionTopic(@Param("userName") String userName) {
        List<AttentionResponse> res = userCollectManage.getAttentionTopic(userName);
        return ResultFactory.buildSuccessResult(null, res);
    }

    @GetMapping("/getPublicInfos")
    public Result getPublicInfos(@Param("userName") String userName) {
        List<PublishInfoResponse> res = personalManage.getPublics(userName);
        return ResultFactory.buildSuccessResult("", res);
    }

    @GetMapping("getTopics")
    public Result getTopics(@Param("userName") String userName) {
        List<AttentionResponse> res = personalManage.getTopics(userName);
        return ResultFactory.buildSuccessResult(null, res);
    }

    @PostMapping("addTopic")
    public Result addTopic(@RequestBody AddTopicRequest addTopicRequest) {
        personalManage.addTopic(addTopicRequest);
        return ResultFactory.buildSuccessResult(null, null);
    }

    @GetMapping("getConditions")
    public Result getConditions(@Param("userName") String userName) {
        Integer userId = userService.findUserByName(userName).getId();
        List<ConditionResponse> res = personalManage.getConditions(userId);
        return ResultFactory.buildSuccessResult(null, res);
    }


}
