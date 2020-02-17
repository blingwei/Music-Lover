package com.blingwei.musicService.dao;

import com.blingwei.musicService.pojo.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper {
    void addCommit(Comment comment);
    List<Comment> findEssayWithSongCommentByMatterId(@Param("id") Integer matterId);
    Comment findCommentById(Integer id);
}
