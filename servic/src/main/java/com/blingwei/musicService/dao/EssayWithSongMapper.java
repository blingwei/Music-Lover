package com.blingwei.musicService.dao;

import com.blingwei.musicService.bean.responseBean.PublishInfoResponse;
import com.blingwei.musicService.pojo.EssayWithSong;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EssayWithSongMapper {
    int addEssayWithSong(EssayWithSong eassyWithSong);
    EssayWithSong findEssayWithSongById(Integer id);
    EssayWithSong findEssayWithSongByEssayId(Integer id);
    int getEssayWithSongNumByUserId(Integer userId);

    List<PublishInfoResponse> getPublicInfosByUserName(String userName);
    String  findEssayNameById(Integer id);
}
