package com.blingwei.musicService.utils;

import com.blingwei.musicService.dao.redisService.impl.LikeRedisServiceImpl;
import com.blingwei.musicService.enums.PickStatusEnum;
import com.blingwei.musicService.enums.TypeEnum;
import com.blingwei.musicService.manage.UserPickManage;
import com.blingwei.musicService.pojo.UserPick;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 86187
 * 创建定时任务
 */
@Component
@EnableScheduling //开启定时任务
public class ScheduleTask {

    @Autowired
    private LikeRedisServiceImpl likeRedisService;

    @Autowired
    private UserPickManage userPickManage;

    /**
     * 每隔3个小时把Redis中用户的点赞数据写进数据库
     */
    @Async("scheduledPoolTaskExecutor")
    @Scheduled(cron = "0 0 0/3 * * ? ")
    public void insertUserPick(){
        Map<String, Integer> essayWithSongPickMap = likeRedisService.getAllEssayWithSongPick();
        List<UserPick> userPicks = new ArrayList();
        List<UserPick> userExistPicks = new ArrayList<>();
        for(Map.Entry<String, Integer> entry:essayWithSongPickMap.entrySet()){
            UserPick userPick = new UserPick();
            String[] k = entry.getKey().split(":");
            userPick.setUserId(Integer.parseInt(k[0]));
            userPick.setMatterId(Integer.parseInt(k[1]));
            userPick.setStatus(PickStatusEnum.valueOf(entry.getValue()));
            userPick.setType(TypeEnum.ESSAY_WITH_SONG);
            if(userPickManage.isExist(userPick)){
                userExistPicks.add(userPick);
            }else{
                userPicks.add(userPick);
            }

        }

        Map<String, Integer> commentPickMap = likeRedisService.getAllCommentPick();
        for(Map.Entry<String, Integer> entry:commentPickMap.entrySet()){
            UserPick userPick = new UserPick();
            String[] k = entry.getKey().split(":");
            userPick.setUserId(Integer.parseInt(k[0]));
            userPick.setMatterId(Integer.parseInt(k[1]));
            userPick.setStatus(PickStatusEnum.valueOf(entry.getValue()));
            userPick.setType(TypeEnum.COMMENT);
            if(userPickManage.isExist(userPick)){
                userExistPicks.add(userPick);
            }else{
                userPicks.add(userPick);
            }
        }
        if(!CollectionUtils.isEmpty(userPicks)){
            userPickManage.insertUserPicks(userPicks);
        }
        if(!CollectionUtils.isEmpty(userExistPicks)){
            userPickManage.insertExistUserPicks(userExistPicks);
        }
        likeRedisService.clean();
        System.out.println("执行定时任务");

    }


}
