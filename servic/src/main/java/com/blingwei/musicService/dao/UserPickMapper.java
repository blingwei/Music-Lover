package com.blingwei.musicService.dao;

import com.blingwei.musicService.pojo.UserPick;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 86187
 */
@Repository

public interface UserPickMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPick record);

    UserPick selectByPrimaryKey(Integer id);

    List<UserPick> selectAll();

    int updateByPrimaryKey(UserPick record);

    Integer getEssayWithSongPickNumByMatterId(@Param("matterId") Integer matterId);

    UserPick getEssayWithSongPickByUserIdAndMatterId(@Param("userId") Integer userId, @Param("matterId") Integer matterId);

    Integer getCommentPickNumByMatterId(@Param("matterId") Integer matterId);

    UserPick getCommentPickByUserIdAndMatterId(@Param("userId") Integer userId, @Param("matterId") Integer matterId);

    int insertUserPicks(List<UserPick> userPicks);

    int insertExistUserPicks(List<UserPick> userPicks);


}