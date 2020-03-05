package com.blingwei.musicService.dao;

import com.blingwei.musicService.bean.commanBean.PublishManageBean;
import com.blingwei.musicService.bean.responseBean.AdminViewPublishResponse;
import com.blingwei.musicService.bean.responseBean.PublishInfoResponse;
import com.blingwei.musicService.bean.commanBean.PublishVerifyBean;
import com.blingwei.musicService.pojo.EssayForElastic;
import com.blingwei.musicService.pojo.EssayWithSong;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EssayWithSongMapper {
    int addEssayWithSong(EssayWithSong eassyWithSong);

    EssayWithSong findEssayWithSongById(Integer id);

    EssayWithSong findEssayWithSongByEssayId(Integer id);

    int getEssayWithSongNumByUserId(Integer userId);

    List<PublishInfoResponse> getPublicInfosByUserName(String userName);

    String findEssayNameById(Integer id);

    List<PublishVerifyBean> getPublishesWithoutVerify(@Param("start") Integer start, @Param("size") Integer size);//用户未审核，刚发表的作品

    int getAllPublishNums();

    AdminViewPublishResponse getPublishView(Integer id);

    void AdminVerifyPublish(@Param("id") Integer id, @Param("status") Integer status);

    List<PublishManageBean> getPublishesWithRestrict(@Param("start") Integer start, @Param("size") Integer size, @Param("input") String input, @Param("status") Integer status);

    int getPublishesWithRestrictNums(@Param("start") Integer start, @Param("size") Integer size, @Param("input") String input, @Param("status") Integer status);

    void deltePublish(int id);

    EssayForElastic findEssayForElasticById(Integer id);
}
