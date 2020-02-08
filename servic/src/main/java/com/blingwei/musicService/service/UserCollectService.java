package com.blingwei.musicService.service;

import com.blingwei.musicService.bean.responseBean.CollectInfoResponse;
import com.blingwei.musicService.pojo.Collect;

import java.util.List;

/**
 * @author 86187
 */
public interface UserCollectService {
    int addCollect(Collect collect);
    int deleteCollect(Collect collect);
    int getCollectNumByMatterIdAndType(Collect collect);
    Collect getCollect(Collect collect);
    Collect getCollectWithoutIsDelete(Collect collect);
    List<CollectInfoResponse> findCollectInfos(Integer userId);
}
