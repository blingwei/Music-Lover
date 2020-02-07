package com.blingwei.musicService.manage;

import com.blingwei.musicService.bean.responseBean.CollectResponse;
import com.blingwei.musicService.enums.TypeEnum;
import com.blingwei.musicService.pojo.Collect;
import com.blingwei.musicService.service.UserCollectService;
import com.blingwei.musicService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 86187
 */
@Component
public class UserCollectManage {

    @Autowired
    private UserCollectService userCollectService;

    @Autowired
    private UserService userService;

    public int collectEssayWithSong(Integer matterId){
        Collect collect = new Collect();
        collect.setMatterId(matterId);
        collect.setUserId(userService.getCurrentUser().getId());
        collect.setType(TypeEnum.ESSAY_WITH_SONG);
        userCollectService.addCollect(collect);
        return userCollectService.getCollectNumByMatterIdAndType(collect);
    }

    public int  cancelCollectEssayWithSong(Integer matterId){
        Collect collect = new Collect();
        collect.setMatterId(matterId);
        collect.setUserId(userService.getCurrentUser().getId());
        collect.setType(TypeEnum.ESSAY_WITH_SONG);
        userCollectService.deleteCollect(collect);
        return userCollectService.getCollectNumByMatterIdAndType(collect);
    }

    public CollectResponse getCollectResponse(Integer matterId){
        Collect collect = new Collect();
        collect.setMatterId(matterId);
        collect.setUserId(userService.getCurrentUser().getId());
        collect.setType(TypeEnum.ESSAY_WITH_SONG);
        CollectResponse collectResponse = new CollectResponse();
        collectResponse.setNum(userCollectService.getCollectNumByMatterIdAndType(collect));
        if(userCollectService.getCollect(collect) != null){
            collectResponse.setCollectStatus(true);
        }else{
            collectResponse.setCollectStatus(false);
        }
        return collectResponse;
    }

}
