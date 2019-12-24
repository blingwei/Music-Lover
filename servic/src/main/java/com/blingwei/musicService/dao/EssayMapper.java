package com.blingwei.musicService.dao;

import com.blingwei.musicService.pojo.Essay;
import org.springframework.stereotype.Repository;
//import org.apache.ibatis.type.JdbcType

@Repository
public interface EssayMapper {
    int addEssay(Essay essay);
    Essay findEssayById(Integer id);
}
