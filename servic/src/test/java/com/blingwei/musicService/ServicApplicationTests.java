package com.blingwei.musicService;

import com.blingwei.musicService.dao.CommentMapper;
import com.blingwei.musicService.enums.TypeEnum;
import com.blingwei.musicService.pojo.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootTest
class ServicApplicationTests {

    @Autowired
    private CommentMapper commitMapper;

    @Test
    void contextLoads() {
        Comment comment = new Comment();
        comment.setUserId(1);
        comment.setMatterId(1);
        comment.setType(TypeEnum.ESSAYWITHSONG);
        comment.setPid(0);
        comment.setContent("你的心");

        commitMapper.addCommit(comment);

    }



}
