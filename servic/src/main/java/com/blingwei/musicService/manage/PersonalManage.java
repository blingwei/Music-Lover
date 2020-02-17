package com.blingwei.musicService.manage;

import com.blingwei.musicService.bean.commanBean.AttentionResponse;
import com.blingwei.musicService.bean.requestBaen.AddTopicRequest;
import com.blingwei.musicService.bean.responseBean.EssayWithSongCardResponse;
import com.blingwei.musicService.bean.responseBean.ConditionResponse;
import com.blingwei.musicService.bean.responseBean.PublishInfoResponse;
import com.blingwei.musicService.dao.ConditionMapper;
import com.blingwei.musicService.enums.TypeEnum;
import com.blingwei.musicService.pojo.Condition;
import com.blingwei.musicService.pojo.Topic;
import com.blingwei.musicService.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonalManage {

    @Autowired
    private UserCollectManage userCollectManage;

    @Autowired
    private EssayWithSongService essayWithSongService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ConditionMapper conditionMapper;

    @Autowired
    private UserCollectService userCollectService;


    public List<EssayWithSongCardResponse> getCollects(String userName){
        return userCollectManage.getCollectsByUserName(userName);
    }

    public int getUserPublishNum(Integer userId){
        return essayWithSongService.getEssayWithSongNumByUserId(userId);
    }

    public List<PublishInfoResponse> getPublics(String userName){
        return essayWithSongService.getPublicInfosByUserName(userName);
    }

    public List<AttentionResponse> getTopics(String userName){
        List<Topic> topics = topicService.findTopicByUserId(userService.findUserByName(userName).getId());
        List<AttentionResponse> res = new ArrayList<>();
        for(Topic topic: topics){
            AttentionResponse attentionResponse = new AttentionResponse();
            attentionResponse.setName(topic.getName());
            attentionResponse.setUrl(topic.getUrl());
            attentionResponse.setIntroduce(topic.getIntroduce());
            attentionResponse.setId(topic.getId());
            attentionResponse.setProductionNum(topicService.findPublishNumById(attentionResponse.getId()));
            attentionResponse.setAttentionNum(userCollectService.getAttentionNumByTopicId(attentionResponse.getId()));
            attentionResponse.setStatus(userCollectManage.meIsAttentionUser(topic.getId(), TypeEnum.TOPIC));
            res.add(attentionResponse);
        }
        return res;
    }
    public int addTopic(AddTopicRequest addTopicRequest){
        Topic topic = new Topic();
        topic.setUserId(userService.getCurrentUser().getId());
        topic.setName(addTopicRequest.getName());
        topic.setLabel(addTopicRequest.getLabel());
        topic.setUrl(addTopicRequest.getUrl());
        topic.setIntroduce(addTopicRequest.getIntroduce());
        return topicService.addTopic(topic);
    }

    public List<ConditionResponse> getConditions(Integer userId){
        List<Condition> conditions = conditionMapper.selectByUserId(userId);
        List<ConditionResponse> res = new ArrayList<>();
        for(Condition condition : conditions){
            Integer matterId = condition.getMatterId();
            ConditionResponse conditionResponse = new ConditionResponse();
            conditionResponse.setMatterId(matterId);
            conditionResponse.setOperate(condition.getOperate().getMessage());
            conditionResponse.setType(condition.getType().getMessage());
            String matterName = "";
            switch (condition.getType()){
                case ESSAY_WITH_SONG:
                {
                    matterName = essayWithSongService.findEssayNameById(matterId);
                    EssayWithSongCardResponse essayWithSongCardResponse = conditionMapper.findConditionMessageByEssayWithSongId(matterId);
                    conditionResponse.setConditionMessage(essayWithSongCardResponse);
                    break;
                }

                case COMMENT:
                {
                    matterName = commentService.findCommentById(matterId).getContent();
                    EssayWithSongCardResponse essayWithSongCardResponse = conditionMapper.findConditionMessageByCommentId(matterId);
                    conditionResponse.setConditionMessage(essayWithSongCardResponse);
                    break;
                }
                case USER:
                {
                    matterName = userService.findUserById(matterId).getUsername();
                    AttentionResponse attentionResponse = conditionMapper.findConditionMessageByUserId(matterId);
                    attentionResponse.setId(matterId);
                    attentionResponse.setProductionNum(essayWithSongService.getEssayWithSongNumByUserId(attentionResponse.getId()));
                    attentionResponse.setAttentionNum(userCollectService.getAttentionNumByUserId(attentionResponse.getId()));
                    attentionResponse.setStatus(userCollectManage.meIsAttentionUser(attentionResponse.getId(), TypeEnum.USER));
                    conditionResponse.setConditionMessage(attentionResponse);
                    break;
                }

                case TOPIC:{
                    matterName = topicService.findTopicById(matterId).getName();
                    AttentionResponse attentionResponse = conditionMapper.findConditionMessageByTopicId(matterId);
                    attentionResponse.setId(matterId);
                    attentionResponse.setProductionNum(topicService.findPublishNumById(attentionResponse.getId()));
                    attentionResponse.setAttentionNum(userCollectService.getAttentionNumByTopicId(attentionResponse.getId()));
                    attentionResponse.setStatus(userCollectManage.meIsAttentionUser(attentionResponse.getId(), TypeEnum.TOPIC));
                    conditionResponse.setConditionMessage(attentionResponse);
                    break;
                }
                default: break;
            }
            conditionResponse.setMatterName(matterName);
            res.add(conditionResponse);
        }
        return res;
    }

}
