package com.blingwei.musicService.service.Impl;

import com.blingwei.musicService.bean.responseBean.PublishInfoResponse;
import com.blingwei.musicService.dao.*;
import com.blingwei.musicService.enums.OperateEnum;
import com.blingwei.musicService.enums.TypeEnum;
import com.blingwei.musicService.pojo.*;
import com.blingwei.musicService.service.EssayWithSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author 86187
 */
@Service
public class EssayWithSongServiceImpl implements EssayWithSongService {

    @Autowired
    private SongMapper songMapper;

    @Autowired
    private EssayMapper essayMapper;

    @Autowired
    private EssayWithSongMapper essayWithSongMapper;

    @Autowired
    private ConditionMapper conditionMapper;


    @Override
    public int addSong(Song song) {
        return songMapper.addSong(song);

    }

    @Override
    public int addEssay(Essay essay)  {
        int res =  essayMapper.addEssay(essay);
        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addEssayWithSong(EssayWithSong essayWithSong, Essay essay) {
        addEssay(essay);
        essayWithSong.setEssayId(essay.getId());
        essayWithSongMapper.addEssayWithSong(essayWithSong);
        Condition condition = new Condition();
        condition.setUserId(essayWithSong.getUserId());
        condition.setMatterId(essayWithSong.getId());
        condition.setType(TypeEnum.ESSAY_WITH_SONG);
        condition.setOperate(OperateEnum.PUBLISH);
        return conditionMapper.insert(condition);
    }

    @Override
    public Song findSongById(Integer id) {
        return songMapper.findById(id);
    }

    @Override
    public EssayWithSong findEssayWithSongById(Integer id) {
        return essayWithSongMapper.findEssayWithSongById(id);
    }

    @Override
    public EssayWithSong findEssayWithSongByEssayId(Integer id) {
        return essayWithSongMapper.findEssayWithSongByEssayId(id);
    }

    @Override
    public String findEssayNameById(Integer id) {
        return essayWithSongMapper.findEssayNameById(id);
    }

    @Override
    public Essay findEssayById(Integer id) {
        return essayMapper.findEssayById(id);
    }

    @Override
    public int getEssayWithSongNumByUserId(Integer userId) {
        return essayWithSongMapper.getEssayWithSongNumByUserId(userId);
    }

    @Override
    public List<PublishInfoResponse> getPublicInfosByUserName(String userName) {
        return essayWithSongMapper.getPublicInfosByUserName(userName);
    }


}
