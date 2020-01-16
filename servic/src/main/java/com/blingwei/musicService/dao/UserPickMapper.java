package com.blingwei.musicService.dao;

import com.blingwei.musicService.enums.TypeEnum;
import com.blingwei.musicService.pojo.UserPick;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface UserPickMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPick record);

    UserPick selectByPrimaryKey(Integer id);

    List<UserPick> selectAll();

    int updateByPrimaryKey(UserPick record);

    Integer findpickNumByMatterId(@Param("matterId") Integer matterId, @Param("type") TypeEnum type);

    UserPick getPickByUserIdAndMatterId(@Param("userId") Integer userId, @Param("matterId") Integer matterId, @Param("type") TypeEnum type);

}