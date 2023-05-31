package com.assignment.suzume.controller;

import com.assignment.suzume.service.GamerService;
import com.assignment.suzume.tictactoe.player.Gamer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/gamer")
public class SignUpController {
    private final GamerService gamerService;

    @Autowired
    public SignUpController(GamerService gamerService) {
        this.gamerService = gamerService;
    }

    @GetMapping("/signup")
    public String signUpPage() {
        return "signuppage";
    }

    @PostMapping("/signup")
    @ResponseBody
    public RedirectView signUp(Gamer gamer) {
        boolean success = gamerService.createGamer(gamer);
        if (success) {
            return new RedirectView("/loginpage.html");
        } else {
            return new RedirectView("/signuppage.html");
        }
    }

    @GetMapping("/checkUsername")
    @ResponseBody
    public Map<String, Boolean> checkUsernameExists(@RequestParam("username") String username) {
        boolean exists = gamerService.checkUsernameExists(username);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return response;
    }


//    @PostMapping("/signup")
//    public RedirectView signUp(Gamer gamer) {
//        boolean usernameExists = gamerService.checkUsernameExists(gamer.getUsername());
//        if (usernameExists) {
//            // Username already exists, show an error message
//            return new RedirectView("/signuppage.html?error=username_exists");
//        }
//
//        boolean success = gamerService.createGamer(gamer);
//        if (success) {
//            return new RedirectView("/loginpage.html");
//        } else {
//            return new RedirectView("/signuppage.html");
//        }
//    }

}
