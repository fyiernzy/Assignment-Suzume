package com.assignment.suzume.mapper;

import com.assignment.suzume.tictactoe.player.Gamer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GamerMapper {
    Gamer getGamerByUsername(String username);
    void insertGamer(Gamer gamer);
    void updateGamer(Gamer gamer);
}
