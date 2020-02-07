package com.blingwei.musicService.service;

import com.blingwei.musicService.bean.responseBean.PickResponse;
import com.blingwei.musicService.dao.UserPickMapper;
import com.blingwei.musicService.pojo.UserPick;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author 86187
 */
public interface UserPickService {
    int getEssayWithSongPickNum(Integer matterId);

    UserPick getEssayWithSongPickByUserIdAndMatterId(Integer userId, Integer matterId);

    int getCommentPickNum(Integer matterId);

    UserPick getCommentPickByUserIdAndMatterId(Integer userId, Integer matterId);

    int insertUserPicks(List<UserPick> userPicks);

    int insertExistUserPicks(List<UserPick> userPicks);
}
