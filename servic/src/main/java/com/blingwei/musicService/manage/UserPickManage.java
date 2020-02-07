package com.blingwei.musicService.manage;

import com.blingwei.musicService.bean.responseBean.PickResponse;
import com.blingwei.musicService.dao.UserPickMapper;
import com.blingwei.musicService.dao.redisService.LikeRedisService;
import com.blingwei.musicService.dao.redisService.impl.LikeRedisServiceImpl;
import com.blingwei.musicService.enums.TypeEnum;
import com.blingwei.musicService.pojo.UserPick;
import com.blingwei.musicService.service.UserPickService;
import com.blingwei.musicService.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 86187
 */
@Component
public class UserPickManage {
    @Autowired
    private UserPickService userPickService;
    @Autowired
    private LikeRedisServiceImpl likeRedisService;
    @Autowired
    private UserService userService;

    public PickResponse getEssayWithSongPickResponse(Integer matterId) { //先从redis中取，若没有则从数据库中取
        PickResponse response = new PickResponse();
        Integer userId = userService.findUserByName(SecurityUtils.getSubject().getPrincipal() + "").getId();
        int num;
        if (likeRedisService.getPickEssayWithSongNum(matterId + "") != null) {
            num = likeRedisService.getPickEssayWithSongNum(matterId + "");
        }else{
            num = userPickService.getEssayWithSongPickNum(matterId);
        }
        response.setNum(num);

        boolean pickStatus = false;
        if(likeRedisService.getPickEssayWithSongStatus(userId+"", matterId+"") != null){
            pickStatus = likeRedisService.getPickEssayWithSongStatus(userId+"", matterId+"") != 0;
        }else{
            if(userPickService.getEssayWithSongPickByUserIdAndMatterId(userId, matterId) != null){
                pickStatus = userPickService.getEssayWithSongPickByUserIdAndMatterId(userId, matterId).getStatus().getValue() != 0;
            }
        }
        response.setPickStatus(pickStatus);
        return response;
    }

    public PickResponse getCommentResponse(Integer matterId, Integer userId) { //先从数据库中取，若没有则从redis中取
        PickResponse response = new PickResponse();

        int num;

        if (likeRedisService.getPickCommentNum(matterId + "") != null) {
            num = likeRedisService.getPickCommentNum(matterId + "");
        }else{
            num = userPickService.getCommentPickNum(matterId);
        }
        response.setNum(num);

        boolean pickStatus = false;
        if(likeRedisService.getPickCommentStatus(userId+"", matterId+"") != null){
            pickStatus = likeRedisService.getPickCommentStatus(userId+"", matterId+"") != 0;
        }else{
            if(userPickService.getCommentPickByUserIdAndMatterId(userId, matterId)!=null){
                pickStatus = userPickService.getCommentPickByUserIdAndMatterId(userId, matterId).getStatus().getValue() != 0;
            }

        }
        response.setPickStatus(pickStatus);
        return response;
    }

    public void insertUserPicks(List<UserPick> userPicks){
        //后期分批处理
        userPickService.insertUserPicks(userPicks);

    }

    public void insertExistUserPicks(List<UserPick> userPicks){
        //后期分批处理
        userPickService.insertExistUserPicks(userPicks);

    }

    public boolean isExist(UserPick userPick){
        if( userPickService.getEssayWithSongPickByUserIdAndMatterId(userPick.getUserId(), userPick.getMatterId())!=null){
            userPick.setId(userPickService.getEssayWithSongPickByUserIdAndMatterId(userPick.getUserId(), userPick.getMatterId()).getId());
            return true;
        }else if(userPickService.getCommentPickByUserIdAndMatterId(userPick.getUserId(), userPick.getMatterId())!=null){
            userPick.setId(userPickService.getCommentPickByUserIdAndMatterId(userPick.getUserId(), userPick.getMatterId()).getId());
            return true;
        }else{
            return false;
        }
    }

}
