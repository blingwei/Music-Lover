package com.blingwei.musicService.service.Impl;

import com.blingwei.musicService.dao.EssayMapper;
import com.blingwei.musicService.dao.EssayWithSongMapper;
import com.blingwei.musicService.dao.SongMapper;
import com.blingwei.musicService.pojo.Essay;
import com.blingwei.musicService.pojo.EssayWithSong;
import com.blingwei.musicService.pojo.Song;
import com.blingwei.musicService.service.EssayWithSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
public class EssayWithSongServiceImpl implements EssayWithSongService {

    @Autowired
    private SongMapper songMapper;

    @Autowired
    private EssayMapper essayMapper;

    @Autowired
    private EssayWithSongMapper essayWithSongMapper;


    @Override
    public int addSong(Song song) {
        return songMapper.addSong(song);

    }

    @Override
    public int addEssay(Essay essay)  {
        int res =  essayMapper.addEssay(essay);
        return res;
    }

    @Transactional
    @Override
    public int addEssayWithSong(EssayWithSong essayWithSong, Essay essay) {
        addEssay(essay);
        essayWithSong.setEssayId(essay.getId());
        return essayWithSongMapper.addEssayWithSong(essayWithSong);
    }

    @Override
    public Song findSongById(Integer id) {
        return songMapper.findById(id);
    }

    @Override
    public EssayWithSong findEssayWithSongById(Integer id) {
        return essayWithSongMapper.findEssayWithSongById(id);
    }

    @Override
    public EssayWithSong findEssayWithSongByEssayId(Integer id) {
        return essayWithSongMapper.findEssayWithSongByEssayId(id);
    }

    @Override
    public Essay findEssayById(Integer id) {
        return essayMapper.findEssayById(id);
    }


}
