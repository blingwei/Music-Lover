package com.blingwei.musicService.dao;

import com.blingwei.musicService.pojo.Label;

import java.util.List;

public interface LabelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Label record);

    Label selectByPrimaryKey(Integer id);

    List<Label> selectAll();

    int updateByPrimaryKey(Label record);
}