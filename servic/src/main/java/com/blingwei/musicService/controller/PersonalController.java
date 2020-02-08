package com.blingwei.musicService.controller;

import com.blingwei.musicService.bean.responseBean.CollectInfoResponse;
import com.blingwei.musicService.manage.PersonalManage;
import com.blingwei.musicService.result.Result;
import com.blingwei.musicService.utils.ResultFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 86187
 */
@RestController
@RequestMapping("/api")
public class PersonalController {
    @Autowired
    private PersonalManage personalManage;

    @GetMapping("/getCollectInfos")
    public Result getCollectInfos(@Param("userName") String userName){
        List<CollectInfoResponse> res = personalManage.getCollects(userName);
        return ResultFactory.buildSuccessResult("", res);
    }
}
