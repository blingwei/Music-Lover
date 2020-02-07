package com.blingwei.musicService.service;

import com.blingwei.musicService.pojo.Collect;

/**
 * @author 86187
 */
public interface UserCollectService {
    int addCollect(Collect collect);
    int deleteCollect(Collect collect);
    int getCollectNumByMatterIdAndType(Collect collect);
    Collect getCollect(Collect collect);
    Collect getCollectWithoutIsDelete(Collect collect);
}
