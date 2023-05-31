package com.assignment.suzume.service;

import com.assignment.suzume.mapper.GamerMapper;
import com.assignment.suzume.tictactoe.player.Gamer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GamerService {
    private final GamerMapper gamerMapper;

    @Autowired
    public GamerService(GamerMapper gamerMapper) {
        this.gamerMapper = gamerMapper;
    }

    public boolean checkUsernameExists(String username) {
        Gamer existingGamer = gamerMapper.getGamerByUsername(username);
        return existingGamer != null;
    }


    @Transactional
    public boolean createGamer(Gamer gamer) {
        Gamer existingGamer = gamerMapper.getGamerByUsername(gamer.getUsername());
        if (existingGamer != null) {
            // Gamer with the given username already exists
            return false;
        }

        gamerMapper.insertGamer(gamer);
        return true;
    }
}
