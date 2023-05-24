package com.assignment.suzume.service;

import com.assignment.suzume.mapper.GamerMapper;
import com.assignment.suzume.tictactoe.player.Gamer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GamerService {

    private final GamerMapper gamerMapper;

    @Autowired
    public GamerService(GamerMapper gamerMapper) {
        this.gamerMapper = gamerMapper;
    }

    public void insertGamer(Gamer gamer) {
        gamerMapper.insertGamer(gamer);
    }
}
