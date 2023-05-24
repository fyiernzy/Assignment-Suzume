package com.assignment.suzume.controller;

import com.assignment.suzume.mapper.GamerMapper;
import com.assignment.suzume.tictactoe.player.Gamer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/signup")
public class SignUpController {
    private final GamerMapper gamerMapper;

    @Autowired
    public SignUpController(GamerMapper gamerMapper) {
        this.gamerMapper = gamerMapper;
    }

    @PostMapping
    public String signUp(@RequestParam("username") String username,
                         @RequestParam("password") String password) {
        System.out.println(username);
        System.out.println(password);
        Gamer gamer = new Gamer(username, password);
        gamerMapper.insertGamer(gamer);
        System.out.println("Record inserted successfully");

        return "signup-success"; // Return the appropriate view name or redirect to a different URL
    }
}
