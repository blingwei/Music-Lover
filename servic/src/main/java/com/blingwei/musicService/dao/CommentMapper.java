package com.blingwei.musicService.dao;

import com.blingwei.musicService.pojo.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMapper {
    void addCommit(Comment comment);
}
