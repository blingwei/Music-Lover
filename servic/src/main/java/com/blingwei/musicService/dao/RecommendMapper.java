package com.blingwei.musicService.dao;

import com.blingwei.musicService.pojo.Recommend;

import java.util.List;

public interface RecommendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Recommend record);

    Recommend selectByPrimaryKey(Integer id);

    List<Recommend> selectAll();

    int updateByPrimaryKey(Recommend record);
}