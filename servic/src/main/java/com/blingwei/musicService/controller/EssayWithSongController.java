package com.blingwei.musicService.controller;

import com.blingwei.musicService.dao.redisService.LikeRedisService;
import com.blingwei.musicService.dao.redisService.impl.LikeRedisServiceImpl;
import com.blingwei.musicService.pojo.*;
import com.blingwei.musicService.result.Result;
import com.blingwei.musicService.service.EssayWithSongService;
import com.blingwei.musicService.service.UserService;
import com.blingwei.musicService.dao.esServise.EssayWithSongEsService;
import com.blingwei.musicService.utils.ResultFactory;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@RestController
@RequestMapping("/api")
public class EssayWithSongController {

    @Autowired
    private EssayWithSongService essayWithSongService;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeRedisServiceImpl likeRedisService;


    @RequestMapping("creation/uploadSong")
    public Result uploadSong(@RequestBody MultipartFile file){
        String folder = "D:\\myProject\\Music Lover\\client\\static\\audio";
        File imageFolder = new File(folder);
        String filename = file.getOriginalFilename();
        String suffix = ".mp3";
        if( filename.split("\\.").length>1) {
            suffix = filename.split("\\.")[filename.split("\\.").length - 1];
        }
        File f = new File(imageFolder, getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try{
            file.transferTo(f);
            Song song = new Song();
            String songName = filename.substring(0, filename.length()-suffix.length());
            song.setName(songName);
            song.setUrl(f.getAbsolutePath());
            int songId =  essayWithSongService.addSong(song);
            return ResultFactory.buildSuccessResult("上传成功", song.getId());
        }catch (Exception e){
            return ResultFactory.buildFailResult("发生未知错误");
        }
    }

    public String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    @RequestMapping("creation/upload")
    public Result upload(@RequestBody Map<String, String> data){
        int songId = Integer.parseInt(data.get("songId")) ;
        String title = data.get("title");
        String intor = data.get("intor");
        String content = data.get("content");
        Essay essay = new Essay();
        essay.setIntor(intor);
        essay.setTitle(title);
        essay.setContent(content);
        EssayWithSong essayWithSong = new EssayWithSong();
        essayWithSong.setSongId(songId);
        Subject subject = SecurityUtils.getSubject();
        User user = userService.findUserByName((String)subject.getPrincipal());
        essayWithSong.setUserId(user.getId());
        try {
            essayWithSongService.addEssayWithSong(essayWithSong, essay);
            EssayForElastic essayForElastic = new EssayForElastic(essay.getId(), essay.getTitle(), essay.getIntor(), essay.getContent(), essayWithSongService.findSongById(songId).getName(), user.getUsername());
            EssayWithSongEsService.add(essayForElastic);
            return ResultFactory.buildSuccessResult("上传成功", null);
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult("发生未知错误");
        }
    }

    @RequestMapping("musicSea/findAllEssayWithSong")
    public Result findAllEssayWithSong(@Param("message") String message){
        try {
            List<Map<String, Object>> resultList = new ArrayList<>();
            List<EssayForElastic> essays = EssayWithSongEsService.find(message);
            for(EssayForElastic essayForElastic: essays){
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("essay", essayForElastic);
                EssayWithSong essayWithSong = essayWithSongService.findEssayWithSongByEssayId(essayForElastic.getId());
                Integer heat = essayWithSong.getHeat();
                resultMap.put("heat", heat);
                resultList.add(resultMap);
            }
            return ResultFactory.buildSuccessResult(null, resultList);
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult("发生未知错误");
        }
    }

    @RequestMapping("musicSea/findEssayWithSongByEssayId")
    public Result findEssayWithSongByEssayId(@Param("id") Integer id){
        try {
            EssayWithSong essayWithSong = essayWithSongService.findEssayWithSongByEssayId(id);
            Essay essay = essayWithSongService.findEssayById(id);
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("essay", essay);
            resultMap.put("essayWithSong" , essayWithSong);
            Song song = essayWithSongService.findSongById(essayWithSong.getSongId());
            resultMap.put("song", song);
            String pickNum = likeRedisService.getPickEssayWithSongNum(id+"")+"";
            resultMap.put("pickNum", pickNum);
            return ResultFactory.buildSuccessResult(null, resultMap);
        }catch (Exception e){
            return ResultFactory.buildFailResult(null);
        }

    }

    @RequestMapping("pickEssayWithSong")
    public Result pickEssayWithSong(@Param("matterId") String matterId){
        String userId = userService.findUserByName(SecurityUtils.getSubject().getPrincipal()+ "").getId() + ""  ;
        likeRedisService.pickEssayWithSong(userId, matterId);
        String pickNum = likeRedisService.getPickEssayWithSongNum(matterId) + "";
        return ResultFactory.buildSuccessResult(null, pickNum);
    }

    @RequestMapping("cancelPickEssayWithSong")
    public Result cancelPickEssayWithSong(@Param("matterId") String matterId){
        String userId = userService.findUserByName(SecurityUtils.getSubject().getPrincipal()+ "").getId() + ""  ;
        likeRedisService.cancelPickEssayWithSong(userId, matterId);
        String pickNum = likeRedisService.getPickEssayWithSongNum(matterId) + "";
        return ResultFactory.buildSuccessResult(null, pickNum);
    }

    @RequestMapping("getEssayWithSongNumAndStatus")
    public Result getEssayWithSongNum(@Param("matterId") String matterId){
        Integer num = likeRedisService.getPickEssayWithSongNum(matterId);
        String userId = userService.findUserByName(SecurityUtils.getSubject().getPrincipal()+ "").getId() + ""  ;
        boolean pickStatus = false;
        try{
             pickStatus= likeRedisService.getPickEssayWithSongStatus(userId, matterId) != 0;
        }catch (Exception e){
            pickStatus = false;
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("num", num);
        resultMap.put("pickStatus", pickStatus);
        return ResultFactory.buildSuccessResult("", resultMap);
    }





}
