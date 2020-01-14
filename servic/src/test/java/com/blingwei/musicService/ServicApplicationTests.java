package com.blingwei.musicService;

import com.blingwei.musicService.dao.CommentMapper;
import com.blingwei.musicService.dao.redisService.LikeRedisService;
import com.blingwei.musicService.dao.redisService.impl.LikeRedisServiceImpl;
import com.blingwei.musicService.enums.TypeEnum;
import com.blingwei.musicService.pojo.Comment;
import net.sf.json.JSON;
import org.apache.shiro.SecurityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@SpringBootTest
class ServicApplicationTests {

    @Autowired
    private CommentMapper commitMapper;

    @Autowired
    private LikeRedisServiceImpl likeRedisService;

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        Comment comment = new Comment();
        comment.setUserId(1);
        comment.setMatterId(1);
        comment.setType(TypeEnum.ESSAY_WITH_SONG);
        comment.setPid(0);
        comment.setContent("你的心");
        commitMapper.addCommit(comment);
    }

    @Test
    void findCommentByMatterIdTest(){
        List<Comment> comments = commitMapper.findEssayWithSongCommentByMatterId(1);
        comments.size();
    }


    @Test
    void redisTest(){
        likeRedisService.pickEssayWithSong("13","13");
//        likeRedisService.getPickEssayWithSongNum("13");
        System.out.println(likeRedisService.getPickEssayWithSongNum("13"));
//        likeRedisService.cancelPickEssayWithSong("12","13");

    }


//    @Test
//    void test(){
//        SecurityUtils.getSubject().getPrincipal();
//        System.out.println();
//    }



}
