package com.blingwei.musicService.service;

import com.blingwei.musicService.pojo.Collect;
import com.blingwei.musicService.pojo.Essay;
import com.blingwei.musicService.pojo.EssayWithSong;
import com.blingwei.musicService.pojo.Song;

import java.io.IOException;

/**
 * @author 86187
 */
public interface EssayWithSongService {
    int addSong(Song song);
    int addEssay(Essay essay) throws IOException;
    int addEssayWithSong(EssayWithSong essayWithSong, Essay essay);


    Song findSongById(Integer id);


    EssayWithSong findEssayWithSongById(Integer id);
    EssayWithSong findEssayWithSongByEssayId(Integer id);

    Essay findEssayById(Integer id);


}
