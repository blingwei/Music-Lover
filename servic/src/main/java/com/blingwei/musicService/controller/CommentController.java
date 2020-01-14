package com.blingwei.musicService.controller;

import com.blingwei.musicService.dao.redisService.LikeRedisService;
import com.blingwei.musicService.pojo.Comment;
import com.blingwei.musicService.pojo.client.CommentInfo;
import com.blingwei.musicService.result.Result;
import com.blingwei.musicService.service.CommentService;
import com.blingwei.musicService.service.EssayWithSongService;
import com.blingwei.musicService.service.UserService;
import com.blingwei.musicService.utils.ConvertUtil;
import com.blingwei.musicService.utils.ResultFactory;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private  ConvertUtil convertUtil;

    @Autowired
    private EssayWithSongService essayWithSongService;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeRedisService likeRedisService;

    @RequestMapping("/addComment")
    public Result addComment(@RequestBody CommentInfo commentInfo){
        try{
            Comment comment = convertUtil.covertComment(commentInfo);
            commentService.addCommit(comment);
            return ResultFactory.buildSuccessResult("评论成功", null);
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult("发生未知错误，添加失败");
        }
    }

    @RequestMapping("/findEssayWithSongComment")
    public Result findEssayWithSongComment(@Param("id") Integer id){
        try{
            Integer matterId = essayWithSongService.findEssayWithSongByEssayId(id).getId();
            List<Comment> comments = commentService.findEssayWithSongCommentByMatterId(matterId);
            List<CommentInfo> commentInfoList = convertUtil.showComment(comments);
            return ResultFactory.buildSuccessResult("", commentInfoList);
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult("发生未知错误");
        }
    }

    @RequestMapping("pickComment")
    public Result pickComment(@Param("matterId") String matterId){
        String userId = userService.findUserByName(SecurityUtils.getSubject().getPrincipal()+ "").getId() + ""  ;
        likeRedisService.pickComment(userId, matterId);
        String pickNum = likeRedisService.getPickCommentNum(matterId) + "";
        return ResultFactory.buildSuccessResult(null, pickNum);
    }

    @RequestMapping("cancelPickComment")
    public Result cancelPickComment(@Param("matterId") String matterId){
        String userId = userService.findUserByName(SecurityUtils.getSubject().getPrincipal()+ "").getId() + ""  ;
        likeRedisService.cancelPickComment(userId, matterId);
        String pickNum = likeRedisService.getPickCommentNum(matterId) + "";
        return ResultFactory.buildSuccessResult(null, pickNum);
    }

    @RequestMapping("getCommentNumAndStatus")
    public Result getEssayWithSongNum(@Param("matterId") String matterId){
        Integer num = likeRedisService.getPickCommentNum(matterId);
        String userId = userService.findUserByName(SecurityUtils.getSubject().getPrincipal()+ "").getId() + ""  ;
        boolean pickStatus = false;
        try{
            pickStatus= likeRedisService.getPickCommentStatus(userId, matterId) != 0;
        }catch (Exception e){
            pickStatus = false;
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("num", num);
        resultMap.put("pickStatus", pickStatus);
        return ResultFactory.buildSuccessResult("", resultMap);
    }



}
