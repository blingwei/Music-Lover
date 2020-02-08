package com.blingwei.musicService.manage;

import com.blingwei.musicService.bean.responseBean.CollectInfoResponse;
import com.blingwei.musicService.bean.responseBean.CollectResponse;
import com.blingwei.musicService.pojo.UserInfo;
import com.blingwei.musicService.service.UserCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonalManage {

    @Autowired
    private UserCollectManage userCollectManage;

    public List<CollectInfoResponse> getCollects(String userName){
        return userCollectManage.getCollectsByUserName(userName);
    }
}
