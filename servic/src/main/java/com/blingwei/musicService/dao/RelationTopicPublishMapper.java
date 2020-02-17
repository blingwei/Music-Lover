package com.blingwei.musicService.dao;

import com.blingwei.musicService.pojo.RelationTopicPublish;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 86187
 */
@Repository
public interface RelationTopicPublishMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RelationTopicPublish record);

    RelationTopicPublish selectByPrimaryKey(Integer id);

    List<RelationTopicPublish> selectAll();

    int updateByPrimaryKey(RelationTopicPublish record);
}