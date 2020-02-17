package com.blingwei.musicService.utils;

import com.blingwei.musicService.bean.requestBaen.EditUserInfoRequest;
import com.blingwei.musicService.bean.responseBean.PickResponse;
import com.blingwei.musicService.bean.responseBean.UserInfoResponse;
import com.blingwei.musicService.dao.EssayWithSongMapper;
import com.blingwei.musicService.dao.UserMapper;
import com.blingwei.musicService.dao.redisService.LikeRedisService;
import com.blingwei.musicService.dao.redisService.impl.LikeRedisServiceImpl;
import com.blingwei.musicService.enums.SexEnum;
import com.blingwei.musicService.enums.TypeEnum;
import com.blingwei.musicService.manage.UserCollectManage;
import com.blingwei.musicService.manage.UserPickManage;
import com.blingwei.musicService.pojo.Comment;
import com.blingwei.musicService.pojo.User;
import com.blingwei.musicService.pojo.UserInfo;
import com.blingwei.musicService.pojo.client.CommentInfo;
import com.blingwei.musicService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConvertUtil {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EssayWithSongMapper essayWithSongMapper;

    @Autowired
    private LikeRedisServiceImpl likeRedisService;

    @Autowired
    private UserPickManage userPickManage;

    @Autowired
    private UserService userService;

    @Autowired
    private UserCollectManage collectManage;

    public Comment covertComment(CommentInfo commentInfo){
        Comment comment = new Comment();
        Integer userId = userMapper.findByName(commentInfo.getUsername()).getId();
        comment.setUserId(userId);
        TypeEnum typeEnum = TypeEnum.valueOf(Integer.parseInt(commentInfo.getType()));
        comment.setType(typeEnum);
        Integer matterId = commentInfo.getMatterId();
        switch (typeEnum){
            case ESSAY_WITH_SONG:
                matterId = essayWithSongMapper.findEssayWithSongByEssayId(commentInfo.getMatterId()).getId();
                break;
            default: break;
        }
        comment.setMatterId(matterId);
        if(commentInfo.getPid() != null){
            comment.setPid(commentInfo.getPid());
        }
        if(commentInfo.getReplyId() != null){
            comment.setReplyId(commentInfo.getReplyId());
        }
        comment.setContent(commentInfo.getContent());
        return comment;
    }

    public List<CommentInfo> showComment(List<Comment> comments){
        List<CommentInfo> commentInfoList = new ArrayList();
        for(Comment comment: comments){
            CommentInfo commentInfo = new CommentInfo();
            commentInfo.setUserId(comment.getUserId());
            String username = userMapper.findUserById(comment.getUserId()).getUsername();
            commentInfo.setId(comment.getId());
            commentInfo.setUsername(username);
            commentInfo.setCreateDate(CommonUtil.DateToString(comment.getCreateDate(), "yyyy/MM//dd HH:mm"));
            commentInfo.setContent(comment.getContent());
            commentInfo.setPid(comment.getPid());
            if(comment.getReplyId() != 0){
                String replayName = userMapper.findUserById(comment.getReplyId()).getUsername();
                commentInfo.setReplayName(replayName);
                commentInfo.setReplyId(comment.getReplyId());
            }
            PickResponse pickResponse = userPickManage.getCommentResponse(comment.getId(), comment.getUserId());
            commentInfo.setPickNum(pickResponse.getNum());
            commentInfo.setPickStatus(pickResponse.getPickStatus());
            commentInfoList.add(commentInfo);
        }
        return commentInfoList;
    }

    public UserInfo convertUserInfo(EditUserInfoRequest userInfoRequest){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userInfoRequest.getUserId());
        userInfo.setAge(userInfoRequest.getAge());
        if(!userInfoRequest.getSex().equals(SexEnum.UN_KNOW)){
            userInfo.setSex(SexEnum.valueOf(userInfoRequest.getSex()));
        }
        userInfo.setIntroduce(userInfoRequest.getIntroduce());
        return userInfo;
    }

    public UserInfoResponse showUserInfo(UserInfo userInfo){
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        userInfoResponse.setName(userMapper.findUserById(userInfo.getUserId()).getUsername());
        userInfoResponse.setAge(userInfo.getAge());
        if(userInfo.getSex()!=null){
            userInfoResponse.setSex(userInfo.getSex().getMessage());
        }
        userInfoResponse.setIntroduce(userInfo.getIntroduce());
        userInfoResponse.setStatus(collectManage.meIsAttentionUser(userInfo.getUserId(), TypeEnum.USER));
        return userInfoResponse;
    }

}
