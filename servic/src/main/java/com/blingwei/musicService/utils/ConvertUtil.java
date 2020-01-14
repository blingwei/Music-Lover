package com.blingwei.musicService.utils;

import com.blingwei.musicService.dao.EssayWithSongMapper;
import com.blingwei.musicService.dao.UserMapper;
import com.blingwei.musicService.dao.redisService.LikeRedisService;
import com.blingwei.musicService.enums.TypeEnum;
import com.blingwei.musicService.pojo.Comment;
import com.blingwei.musicService.pojo.client.CommentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConvertUtil {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EssayWithSongMapper essayWithSongMapper;

    @Autowired
    private LikeRedisService likeRedisService;

    public  Comment covertComment(CommentInfo commentInfo){
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
            if(likeRedisService.getPickCommentStatus(comment.getUserId()+"",comment.getMatterId()+"") != null){
                commentInfo.setPickStatus(likeRedisService.getPickCommentStatus(comment.getUserId()+"",comment.getMatterId()+"")!=0);
            }
            if(likeRedisService.getPickCommentNum(comment.getMatterId()+"")!=null){
                commentInfo.setPickNum(likeRedisService.getPickCommentNum(comment.getMatterId()+""));
            }
            commentInfoList.add(commentInfo);
        }
        return commentInfoList;
    }
}
