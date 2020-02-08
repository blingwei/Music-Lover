package com.blingwei.musicService.service.Impl;

import com.blingwei.musicService.bean.responseBean.CollectInfoResponse;
import com.blingwei.musicService.dao.CollectMapper;
import com.blingwei.musicService.pojo.Collect;
import com.blingwei.musicService.service.UserCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCollectServiceImpl implements UserCollectService {

    @Autowired
    private CollectMapper collectMapper;

    @Override
    public int addCollect(Collect collect) {
        if(getCollectWithoutIsDelete(collect)!=null){
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
        return collectMapper.selectByPrimaryKey(collect.getUserId(),collect.getMatterId(),collect.getType());
    }

    @Override
    public Collect getCollectWithoutIsDelete(Collect collect) {
        return collectMapper.selectAllByPrimaryKey(collect.getUserId(),collect.getMatterId(),collect.getType());
    }

    @Override
    public List<CollectInfoResponse> findCollectInfos(Integer userId) {
        return collectMapper.findCollectInfos(userId);
    }
}
