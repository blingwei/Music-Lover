package com.blingwei.musicService.dao;

import com.blingwei.musicService.bean.commanBean.PublishVerifyBean;
import com.blingwei.musicService.bean.commanBean.TopicInfoBean;
import com.blingwei.musicService.bean.responseBean.EssayWithSongCardResponse;
import com.blingwei.musicService.pojo.Topic;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Topic record);

    Topic selectByPrimaryKey(Integer id);

    List<Topic> selectAll();

    List<Topic> selectByUserId(Integer userId);

    int updateByPrimaryKey(Topic record);

    TopicInfoBean findTopicInfoById(Integer id);

    List<EssayWithSongCardResponse> findPublishByTopicId(Integer id);

    void contributeForTopic(@Param("topicId") Integer id, @Param("ids") List<Integer> ids);

    int findPublishNumById(Integer id);

    List<PublishVerifyBean> getTopicsWithoutVerify(@Param("start") Integer start, @Param("size") Integer size);//用户未审核，刚发表的作品
    int getAllTopicNums();

    void AdminVerifyTopic(@Param("id") Integer id, @Param("status") Integer status);
}