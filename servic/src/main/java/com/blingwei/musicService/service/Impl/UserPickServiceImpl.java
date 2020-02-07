package com.blingwei.musicService.service.Impl;

import com.blingwei.musicService.bean.responseBean.PickResponse;
import com.blingwei.musicService.dao.UserPickMapper;
import com.blingwei.musicService.pojo.UserPick;
import com.blingwei.musicService.service.UserPickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 86187
 */
@Service
public class UserPickServiceImpl implements UserPickService {

    @Autowired
    private UserPickMapper userPickMapper;


    @Override
    public int getEssayWithSongPickNum(Integer matterId) {
        return userPickMapper.getEssayWithSongPickNumByMatterId(matterId);
    }

    @Override
    public UserPick getEssayWithSongPickByUserIdAndMatterId(Integer userId, Integer matterId) {
        return userPickMapper.getEssayWithSongPickByUserIdAndMatterId(userId, matterId);
    }


    @Override
    public int getCommentPickNum(Integer matterId) {
        return userPickMapper.getCommentPickNumByMatterId(matterId);

    }

    @Override
    public UserPick getCommentPickByUserIdAndMatterId(Integer userId, Integer matterId) {
        return userPickMapper.getCommentPickByUserIdAndMatterId(userId, matterId);
    }

    @Override
    public int insertUserPicks(List<UserPick> userPicks) {
        return userPickMapper.insertUserPicks(userPicks);
}

    @Override
    public int insertExistUserPicks(List<UserPick> userPicks) {

        return userPickMapper.insertExistUserPicks(userPicks);
    }

}
