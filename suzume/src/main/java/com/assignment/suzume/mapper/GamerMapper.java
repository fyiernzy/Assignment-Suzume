package com.assignment.suzume.mapper;

import com.assignment.suzume.tictactoe.player.Gamer;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.assignment.suzume.mapper")
@Mapper
public interface GamerMapper {
    void insertGamer(Gamer gamer);
}