package com.blingwei.musicService.dao;

import com.blingwei.musicService.pojo.EssayWithSong;
import org.springframework.stereotype.Repository;

@Repository
public interface EssayWithSongMapper {
    int addEssayWithSong(EssayWithSong eassyWithSong);
    EssayWithSong findEssayWithSongById(Integer id);
    EssayWithSong findEssayWithSongByEssayId(Integer id);
}
