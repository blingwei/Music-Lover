package com.blingwei.musicService.dao;

import com.blingwei.musicService.pojo.Song;
import org.springframework.stereotype.Repository;

@Repository
public interface SongMapper {
    int addSong(Song song);

    Song findById(Integer id);
}
