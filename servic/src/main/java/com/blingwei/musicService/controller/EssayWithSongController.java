package com.blingwei.musicService.controller;

import com.blingwei.musicService.bean.responseBean.CollectResponse;
import com.blingwei.musicService.bean.responseBean.PickResponse;
import com.blingwei.musicService.dao.redisService.LikeRedisService;
import com.blingwei.musicService.dao.redisService.impl.LikeRedisServiceImpl;
import com.blingwei.musicService.enums.TypeEnum;
import com.blingwei.musicService.manage.UserCollectManage;
import com.blingwei.musicService.manage.UserPickManage;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.IOException;
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

    @Autowired
    private UserPickManage userPickManage;

    @Autowired
    private UserCollectManage userCollectManage;


    @PostMapping("/uploadImage")
    public Result uploadImage(MultipartFile file) {
        StringBuilder folder = new StringBuilder();
        String path = System.getProperty("user.dir");
        String[] paths = path.split("\\\\");
        for (int i = 0; i < paths.length - 1; i++) {
            folder.append(paths[i]).append("\\");
        }
        folder.append("client\\static\\image");
        File imageFolder = new File(folder.toString());
        String filename = file.getOriginalFilename();
        String suffix = ".jpg";
        if (filename.split("\\.").length > 1) {
            suffix = filename.split("\\.")[filename.split("\\.").length - 1];
        }
        String url = getRandomString(6) + file.getOriginalFilename().substring(file.getOriginalFilename().length() - suffix.length() - 1);
        File f = new File(imageFolder, url);
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        try {
            file.transferTo(f);
            return ResultFactory.buildSuccessResult("图片上传成功", "static/image/" + url);
        } catch (Exception e) {
            return ResultFactory.buildFailResult("发生未知错误");
        }
    }

    @RequestMapping("creation/uploadSong")
    public Result uploadSong(@RequestBody MultipartFile file) {

        StringBuilder folder = new StringBuilder();
        String path = System.getProperty("user.dir");
        String[] paths = path.split("\\\\");
        for (int i = 0; i < paths.length - 1; i++) {
            folder.append(paths[i]).append("\\");
        }
        folder.append("client\\static\\audio");
        File imageFolder = new File(folder.toString());
        String filename = file.getOriginalFilename();
        String suffix = ".mp3";
        if (filename.split("\\.").length > 1) {
            suffix = filename.split("\\.")[filename.split("\\.").length - 1];
        }
        File f = new File(imageFolder, getRandomString(6) + file.getOriginalFilename()
            .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        try {
            file.transferTo(f);
            Song song = new Song();
            String songName = filename.substring(0, filename.length() - suffix.length());
            song.setName(songName);
            song.setUrl(f.getAbsolutePath());
            essayWithSongService.addSong(song);
            return ResultFactory.buildSuccessResult("歌曲上传成功", song.getId());
        } catch (Exception e) {
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
    public Result upload(@RequestBody Map<String, String> data) {
        int songId = Integer.parseInt(data.get("songId"));
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
        User user = userService.findUserByName((String) subject.getPrincipal());
        essayWithSong.setUserId(user.getId());
        try {
            essayWithSongService.addEssayWithSong(essayWithSong, essay);
            return ResultFactory.buildSuccessResult("上传成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultFactory.buildFailResult("发生未知错误");
        }
    }

    @RequestMapping("musicSea/findAllEssayWithSong")
    public Result findAllEssayWithSong(@Param("message") String message, @Param("start") Integer start, @Param("size") Integer size) {
        try {
            Map<String, Object> result = new HashMap<>();
            List<Map<String, Object>> resultList = new ArrayList<>();
            List<EssayForElastic> essays = EssayWithSongEsService.find(message, start, size);
            for (EssayForElastic essayForElastic : essays) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("essay", essayForElastic);
                EssayWithSong essayWithSong = essayWithSongService.findEssayWithSongById(essayForElastic.getId());
//                Integer heat = 0;
//                resultMap.put("heat", heat);
                resultList.add(resultMap);
            }
            result.put("list", resultList);
            result.put("nums", EssayWithSongEsService.findNums(message));
            return ResultFactory.buildSuccessResult(null, result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultFactory.buildFailResult("发生未知错误");
        }
    }

    @RequestMapping("musicSea/findEssayWithSongById")
    public Result findEssayWithSongByEssayId(@Param("id") Integer id) {
        try {
            EssayWithSong essayWithSong = essayWithSongService.findEssayWithSongById(id);
            Essay essay = essayWithSongService.findEssayById(essayWithSong.getEssayId());
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("essay", essay);
            resultMap.put("essayWithSong", essayWithSong);
            Song song = essayWithSongService.findSongById(essayWithSong.getSongId());
            resultMap.put("song", song);
            return ResultFactory.buildSuccessResult(null, resultMap);
        } catch (Exception e) {
            return ResultFactory.buildFailResult(null);
        }

    }

    @RequestMapping("pickEssayWithSong")
    public Result pickEssayWithSong(@Param("matterId") String matterId) {
        String userId = userService.findUserByName(SecurityUtils.getSubject().getPrincipal() + "").getId() + "";
        likeRedisService.pickEssayWithSong(userId, matterId);
        String pickNum = likeRedisService.getPickEssayWithSongNum(matterId) + "";
        return ResultFactory.buildSuccessResult(null, pickNum);
    }

    @RequestMapping("cancelPickEssayWithSong")
    public Result cancelPickEssayWithSong(@Param("matterId") String matterId) {
        String userId = userService.findUserByName(SecurityUtils.getSubject().getPrincipal() + "").getId() + "";
        likeRedisService.cancelPickEssayWithSong(userId, matterId);
        String pickNum = likeRedisService.getPickEssayWithSongNum(matterId) + "";
        return ResultFactory.buildSuccessResult(null, pickNum);
    }

    @RequestMapping("getEssayWithSongPickNumAndStatus")
    public Result getEssayWithSongPickNumAndStatus(@Param("matterId") Integer matterId) {
        PickResponse pickResponse = userPickManage.getEssayWithSongPickResponse(matterId);
        return ResultFactory.buildSuccessResult("", pickResponse);
    }

    @RequestMapping("collectEssayWithSong")
    public Result collectEssayWithSong(@Param("matterId") Integer matterId) {
        int collectNum = userCollectManage.collect(matterId, TypeEnum.ESSAY_WITH_SONG);
        return ResultFactory.buildSuccessResult(null, collectNum);
    }

    @RequestMapping("cancelCollectEssayWithSong")
    public Result cancelCollectEssayWithSong(@Param("matterId") Integer matterId) {
        int collectNum = userCollectManage.cancelCollect(matterId, TypeEnum.ESSAY_WITH_SONG);
        return ResultFactory.buildSuccessResult(null, collectNum);
    }

    @RequestMapping("getEssayWithSongCollectNumAndStatus")
    public Result getEssayWithSongCollectNumAndStatus(@Param("matterId") Integer matterId) {
        CollectResponse collectResponse = userCollectManage.getCollectResponse(matterId);
        return ResultFactory.buildSuccessResult("", collectResponse);
    }


    @GetMapping("getAllRecommend")
    public Result getAllRecommend() {
        return ResultFactory.buildSuccessResult("", essayWithSongService.getAllRecommends());
    }


}
