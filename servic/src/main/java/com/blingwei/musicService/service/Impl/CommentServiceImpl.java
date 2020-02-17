package com.blingwei.musicService.service.Impl;

import com.blingwei.musicService.dao.CommentMapper;
import com.blingwei.musicService.dao.ConditionMapper;
import com.blingwei.musicService.enums.OperateEnum;
import com.blingwei.musicService.enums.TypeEnum;
import com.blingwei.musicService.pojo.Comment;
import com.blingwei.musicService.pojo.Condition;
import com.blingwei.musicService.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 86187
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ConditionMapper conditionMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addCommit(Comment comment) {
        commentMapper.addCommit(comment);
        //增加动态  发表评论
        Condition condition = new Condition();
        condition.setUserId(comment.getUserId());
        condition.setMatterId(comment.getMatterId());
        condition.setType(TypeEnum.COMMENT);
        condition.setOperate(OperateEnum.PUBLISH);
        conditionMapper.insert(condition);
    }

    @Override
    public List<Comment> findEssayWithSongCommentByMatterId(Integer matterId) {
        return commentMapper.findEssayWithSongCommentByMatterId(matterId);
    }

    @Override
    public Comment findCommentById(Integer id) {
        return commentMapper.findCommentById(id);
    }
}
