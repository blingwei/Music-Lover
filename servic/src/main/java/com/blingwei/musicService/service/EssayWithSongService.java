package com.blingwei.musicService.service;

import com.blingwei.musicService.bean.commanBean.PublishManageBean;
import com.blingwei.musicService.bean.responseBean.AdminViewPublishResponse;
import com.blingwei.musicService.bean.responseBean.PublishInfoResponse;
import com.blingwei.musicService.bean.commanBean.PublishVerifyBean;
import com.blingwei.musicService.bean.responseBean.PublishManageResponse;
import com.blingwei.musicService.pojo.*;

import java.io.IOException;
import java.util.List;

/**
 * @author 86187
 */
public interface EssayWithSongService {
    int addSong(Song song);

    int addEssay(Essay essay) throws IOException;

    int addEssayWithSong(EssayWithSong essayWithSong, Essay essay);


    Song findSongById(Integer id);


    EssayWithSong findEssayWithSongById(Integer id);

    EssayWithSong findEssayWithSongByEssayId(Integer id);

    String findEssayNameById(Integer id);

    Essay findEssayById(Integer id);

    int getEssayWithSongNumByUserId(Integer userId);

    List<PublishInfoResponse> getPublicInfosByUserName(String userName);

    List<PublishVerifyBean> getPublishesWithoutVerify(Integer start, Integer size);

    List<PublishManageBean> getPublishesWithRestrict(Integer start, Integer size, String input, Integer status);

    AdminViewPublishResponse getPublishView(Integer id);

    void AdminVerifyPublish(Integer id, Integer status);

    int getAllPublishNums();

    int getPublishesWithRestrictNums(Integer start, Integer size, String input, Integer status);

    void deletePublish(int id);

    EssayForElastic findEssayForElasticById(Integer id);

    List<Recommend> getAllRecommends();//获取所有的推荐

    void recommendEs(Recommend recommend);// 推荐作品
}
