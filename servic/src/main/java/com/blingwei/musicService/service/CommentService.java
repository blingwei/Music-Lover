package com.blingwei.musicService.service;

import com.blingwei.musicService.dao.CommentMapper;
import com.blingwei.musicService.pojo.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CommentService {
    void addCommit(Comment comment);
    List<Comment> findEssayWithSongCommentByMatterId(Integer matterId);
    Comment findCommentById(Integer id);
}
