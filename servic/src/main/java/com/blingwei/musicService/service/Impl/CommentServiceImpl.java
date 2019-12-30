package com.blingwei.musicService.service.Impl;

import com.blingwei.musicService.dao.CommentMapper;
import com.blingwei.musicService.pojo.Comment;
import com.blingwei.musicService.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void addCommit(Comment comment) {
        commentMapper.addCommit(comment);
    }

    @Override
    public List<Comment> findEssayWithSongCommentByMatterId(Integer matterId) {
        return commentMapper.findEssayWithSongCommentByMatterId(matterId);
    }
}
