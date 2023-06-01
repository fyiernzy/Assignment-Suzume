package com.assignment.suzume.controller;

import com.assignment.suzume.service.GamerService;
import com.assignment.suzume.tictactoe.player.Gamer;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/gamer")
public class GamerController {
    private final GamerService gamerService;

    @Autowired
    public GamerController(GamerService gamerService) {
        this.gamerService = gamerService;
    }

    @GetMapping("/signup")
    public String signUpPage() {
        return "signuppage";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "loginpage";
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

    @PostMapping("/login")
    @ResponseBody
    public Map<String, Boolean> login(HttpSession session, @RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        boolean success = gamerService.checkPasswordMatch(username, password);
        Map<String, Boolean> response = new HashMap<>();
        response.put("correct", success);

        if (success) {
            // Create a session and store user information
            session.setAttribute("username", username);
        }

        return response;
    }


    @GetMapping("/checkUsername")
    @ResponseBody
    public Map<String, Boolean> checkUsernameExists(@RequestParam("username") String username) {
        boolean exists = gamerService.checkUsernameExists(username);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return response;
    }

    @PostMapping("/checkPassword")
    @ResponseBody
    public Map<String, Boolean> checkPassword(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        boolean correct = gamerService.checkPasswordMatch(username, password);
        Map<String, Boolean> response = new HashMap<>();
        response.put("correct", correct);
        return response;
    }

//    @GetMapping("/restricted")
//    public String restrictedPage(HttpSession session) {
//        // Check if the user is logged in
//        if (session.getAttribute("username") != null) {
//            // User is logged in, allow access to the restricted page
//            return "restrictedpage";
//        } else {
//            // User is not logged in, redirect to the login page
//            return "redirect:/gamer/login";
//        }
//    }
}