package com.blingwei.musicService.dao;

import com.blingwei.musicService.bean.responseBean.CollectInfoResponse;
import com.blingwei.musicService.enums.TypeEnum;
import com.blingwei.musicService.pojo.Collect;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 86187
 */
@Repository
public interface CollectMapper {
    int deleteByPrimaryKey(Collect record);

    int insert(Collect record);

    Collect selectByPrimaryKey(@Param("userId") Integer userId, @Param("matterId") Integer matterId, @Param("type")TypeEnum type);//is_delete = 0的

    Collect selectAllByPrimaryKey(@Param("userId") Integer userId, @Param("matterId") Integer matterId, @Param("type")TypeEnum type);//包括is_delete = 1的

    List<Collect> selectAll();

    int updateByPrimaryKey(Collect record);

    int getCollectNumByMatterIdAndType(@Param("matterId") Integer matterId, @Param("type")TypeEnum type);

    boolean getCollectStatus(Collect record);

    List<CollectInfoResponse> findCollectInfos(Integer userId);
}