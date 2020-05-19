package com.blingwei.musicService.service.Impl;

import com.blingwei.musicService.bean.commanBean.PublishManageBean;
import com.blingwei.musicService.bean.responseBean.AdminViewPublishResponse;
import com.blingwei.musicService.bean.responseBean.PublishInfoResponse;
import com.blingwei.musicService.bean.commanBean.PublishVerifyBean;
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

    @Autowired
    private RecommendMapper recommendMapper;


    @Override
    public int addSong(Song song) {
        return songMapper.addSong(song);

    }

    @Override
    public int addEssay(Essay essay) {
        int res = essayMapper.addEssay(essay);
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

    @Override
    public List<PublishVerifyBean> getPublishesWithoutVerify(Integer start, Integer size) {
        return essayWithSongMapper.getPublishesWithoutVerify(start, size);
    }

    @Override
    public List<PublishManageBean> getPublishesWithRestrict(Integer start, Integer size, String input, Integer status) {
        return essayWithSongMapper.getPublishesWithRestrict(start, size, input, status);
    }

    @Override
    public AdminViewPublishResponse getPublishView(Integer id) {
        AdminViewPublishResponse adminViewPublishResponse = essayWithSongMapper.getPublishView(id);
        Song song = songMapper.findById(adminViewPublishResponse.getSongId());
        adminViewPublishResponse.setSong(song);
        return adminViewPublishResponse;
    }

    @Override
    public void AdminVerifyPublish(Integer id, Integer status) {
        essayWithSongMapper.AdminVerifyPublish(id, status);
    }

    @Override
    public int getAllPublishNums() {
        return essayWithSongMapper.getAllPublishNums();
    }


    public int getPublishesWithRestrictNums(Integer start, Integer size, String input, Integer status) {
        return essayWithSongMapper.getPublishesWithRestrictNums(start, size, input, status);
    }

    @Override
    public void deletePublish(int id) {
        essayWithSongMapper.deltePublish(id);
    }

    @Override
    public EssayForElastic findEssayForElasticById(Integer id) {
        return essayWithSongMapper.findEssayForElasticById(id);
    }

    @Override
    public List<Recommend> getAllRecommends() {
        return recommendMapper.selectAll();
    }

    @Override
    public void recommendEs(Recommend recommend) {
        recommendMapper.updateByPrimaryKey(recommend);
    }
}
