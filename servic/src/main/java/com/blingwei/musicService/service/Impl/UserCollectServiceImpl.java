package com.blingwei.musicService.service.Impl;

import com.blingwei.musicService.bean.commanBean.AttentionResponse;
import com.blingwei.musicService.bean.responseBean.EssayWithSongCardResponse;
import com.blingwei.musicService.dao.CollectMapper;
import com.blingwei.musicService.dao.ConditionMapper;
import com.blingwei.musicService.enums.OperateEnum;
import com.blingwei.musicService.enums.TypeEnum;
import com.blingwei.musicService.pojo.Collect;
import com.blingwei.musicService.pojo.Condition;
import com.blingwei.musicService.service.UserCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserCollectServiceImpl implements UserCollectService {

    @Autowired
    private CollectMapper collectMapper;

    @Autowired
    private ConditionMapper conditionMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addCollect(Collect collect) {
        Condition condition = new Condition();
        condition.setUserId(collect.getUserId());
        condition.setMatterId(collect.getMatterId());
        condition.setType(collect.getType());
        if (collect.getType() == TypeEnum.ESSAY_WITH_SONG) {
            condition.setOperate(OperateEnum.COLLECT);
        } else {
            condition.setOperate(OperateEnum.ATTENTION);
        }
        conditionMapper.insert(condition);
        if (getCollectWithoutIsDelete(collect) != null) {
            return collectMapper.updateByPrimaryKey(collect);
        }
        return collectMapper.insert(collect);
    }

    @Override
    public int deleteCollect(Collect collect) {
        return collectMapper.deleteByPrimaryKey(collect);
    }

    @Override
    public int getCollectNumByMatterIdAndType(Collect collect) {
        return collectMapper.getCollectNumByMatterIdAndType(collect.getMatterId(), collect.getType());
    }

    @Override
    public Collect getCollect(Collect collect) {
        return collectMapper.selectByPrimaryKey(collect.getUserId(), collect.getMatterId(), collect.getType());
    }

    @Override
    public Collect getCollectWithoutIsDelete(Collect collect) {
        return collectMapper.selectAllByPrimaryKey(collect.getUserId(), collect.getMatterId(), collect.getType());
    }

    @Override
    public List<EssayWithSongCardResponse> findCollectInfos(Integer userId) {
        return collectMapper.findCollectInfos(userId);
    }

    @Override
    public List<AttentionResponse> findMyAttentions(Integer userId) {
        return collectMapper.findMyAttentions(userId);
    }

    @Override
    public List<AttentionResponse> findAttentionMes(Integer userId) {
        return collectMapper.findAttentionMes(userId);
    }

    @Override
    public List<AttentionResponse> findAttentionTopics(Integer userId) {
        return collectMapper.findAttentionTopics(userId);
    }

    @Override
    public int getAttentionNumByUserId(Integer userId) {
        return collectMapper.getAttentionNumByUserId(userId);
    }

    @Override
    public int getAttentionNumByTopicId(Integer topicId) {
        return collectMapper.getAttentionNumByTopicId(topicId);
    }
}
