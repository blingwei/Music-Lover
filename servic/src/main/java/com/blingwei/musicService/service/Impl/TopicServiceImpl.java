package com.blingwei.musicService.service.Impl;

import com.blingwei.musicService.bean.commanBean.PublishVerifyBean;
import com.blingwei.musicService.bean.commanBean.TopicInfoBean;
import com.blingwei.musicService.bean.requestBaen.ContributeForTopicRequest;
import com.blingwei.musicService.bean.responseBean.EssayWithSongCardResponse;
import com.blingwei.musicService.dao.ConditionMapper;
import com.blingwei.musicService.dao.TopicMapper;
import com.blingwei.musicService.enums.OperateEnum;
import com.blingwei.musicService.enums.TypeEnum;
import com.blingwei.musicService.pojo.Condition;
import com.blingwei.musicService.pojo.Topic;
import com.blingwei.musicService.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private ConditionMapper conditionMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addTopic(Topic topic) {
        topicMapper.insert(topic);
        Condition condition = new Condition();
        condition.setUserId(topic.getUserId());
        condition.setMatterId(topic.getId());
        condition.setType(TypeEnum.TOPIC);
        condition.setOperate(OperateEnum.PUBLISH);
        return conditionMapper.insert(condition);
    }

    @Override
    public List<Topic> findTopicByUserId(Integer userId) {
        return topicMapper.selectByUserId(userId);
    }

    @Override
    public Topic findTopicById(Integer id) {
        return topicMapper.selectByPrimaryKey(id);
    }

    @Override
    public TopicInfoBean findTopicInfoById(Integer id) {
        return topicMapper.findTopicInfoById(id);
    }

    @Override
    public List<EssayWithSongCardResponse> findPublishByTopicId(Integer id) {
        return topicMapper.findPublishByTopicId(id);
    }

    @Override
    public void contributeForTopic(ContributeForTopicRequest request) {
        topicMapper.contributeForTopic(request.getTopicId(), request.getPublishIdList());
    }

    @Override
    public int findPublishNumById(Integer id) {
        return topicMapper.findPublishNumById(id);
    }

    @Override
    public List<PublishVerifyBean> getTopicsWithoutVerify(Integer start, Integer size) {
        return topicMapper.getTopicsWithoutVerify(start, size);
    }

    @Override
    public int getAllTopicNums() {
        return topicMapper.getAllTopicNums();
    }

    @Override
    public void AdminVerifyTopic(Integer id, Integer status) {
        topicMapper.AdminVerifyTopic(id, status);
    }
}
