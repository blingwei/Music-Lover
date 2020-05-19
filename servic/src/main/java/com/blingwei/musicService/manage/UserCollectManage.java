package com.blingwei.musicService.manage;

import com.blingwei.musicService.bean.commanBean.AttentionResponse;
import com.blingwei.musicService.bean.responseBean.EssayWithSongCardResponse;
import com.blingwei.musicService.bean.responseBean.CollectResponse;
import com.blingwei.musicService.enums.TypeEnum;
import com.blingwei.musicService.pojo.Collect;
import com.blingwei.musicService.service.EssayWithSongService;
import com.blingwei.musicService.service.TopicService;
import com.blingwei.musicService.service.UserCollectService;
import com.blingwei.musicService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 86187
 */
@Component
public class UserCollectManage {

    @Autowired
    private UserCollectService userCollectService;

    @Autowired
    private UserService userService;

    @Autowired
    private EssayWithSongService essayWithSongService;

    @Autowired
    private TopicService topicService;


    public int collect(Integer matterId, TypeEnum type) {
        Collect collect = new Collect();
        collect.setMatterId(matterId);
        collect.setUserId(userService.getCurrentUser().getId());
        collect.setType(type);
        userCollectService.addCollect(collect);
        return userCollectService.getCollectNumByMatterIdAndType(collect);
    }

    public int cancelCollect(Integer matterId, TypeEnum type) {
        Collect collect = new Collect();
        collect.setMatterId(matterId);
        collect.setUserId(userService.getCurrentUser().getId());
        collect.setType(type);
        userCollectService.deleteCollect(collect);
        return userCollectService.getCollectNumByMatterIdAndType(collect);
    }

    public CollectResponse getCollectResponse(Integer matterId) {
        Collect collect = new Collect();
        collect.setMatterId(matterId);
        collect.setUserId(userService.getCurrentUser().getId());
        collect.setType(TypeEnum.ESSAY_WITH_SONG);
        CollectResponse collectResponse = new CollectResponse();
        collectResponse.setNum(userCollectService.getCollectNumByMatterIdAndType(collect));
        if (userCollectService.getCollect(collect) != null) {
            collectResponse.setCollectStatus(true);
        } else {
            collectResponse.setCollectStatus(false);
        }
        return collectResponse;
    }

    public boolean meIsAttentionUser(Integer matterId, TypeEnum type) {
        Collect collect = new Collect();
        collect.setMatterId(matterId);
        collect.setType(type);
        collect.setUserId(userService.getCurrentUser().getId());
        if (userCollectService.getCollect(collect) != null) {
            return true;
        }
        return false;
    }

    public List<EssayWithSongCardResponse> getCollectsByUserName(String userName) {
        Integer userId = userService.findUserByName(userName).getId();
        return userCollectService.findCollectInfos(userId);
    }

    public List<AttentionResponse> getMyAttention(String userName) {
        Integer userId = userService.findUserByName(userName).getId();
        List<AttentionResponse> res = userCollectService.findMyAttentions(userId);
        for (AttentionResponse attentionResponse : res) {
            attentionResponse.setProductionNum(essayWithSongService.getEssayWithSongNumByUserId(attentionResponse.getId()));
            attentionResponse.setAttentionNum(userCollectService.getAttentionNumByUserId(attentionResponse.getId()));
            attentionResponse.setStatus(meIsAttentionUser(attentionResponse.getId(), TypeEnum.USER));
        }
        return res;
    }

    public List<AttentionResponse> getAttentionMe(String userName) {
        Integer userId = userService.findUserByName(userName).getId();
        List<AttentionResponse> res = userCollectService.findAttentionMes(userId);
        for (AttentionResponse attentionResponse : res) {
            attentionResponse.setProductionNum(essayWithSongService.getEssayWithSongNumByUserId(attentionResponse.getId()));
            attentionResponse.setAttentionNum(userCollectService.getAttentionNumByUserId(attentionResponse.getId()));
            attentionResponse.setStatus(meIsAttentionUser(attentionResponse.getId(), TypeEnum.USER));
        }
        return res;
    }

    public List<AttentionResponse> getAttentionTopic(String userName) {
        Integer userId = userService.findUserByName(userName).getId();
        List<AttentionResponse> res = userCollectService.findAttentionTopics(userId);
        for (AttentionResponse attentionResponse : res) {
            attentionResponse.setProductionNum(topicService.findPublishNumById(attentionResponse.getId()));
            attentionResponse.setAttentionNum(userCollectService.getAttentionNumByTopicId(attentionResponse.getId()));
            attentionResponse.setStatus(meIsAttentionUser(attentionResponse.getId(), TypeEnum.TOPIC));
        }
        return res;
    }

}
