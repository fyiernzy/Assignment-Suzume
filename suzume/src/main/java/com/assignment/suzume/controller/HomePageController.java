package com.assignment.suzume.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
public class HomePageController {
    @RequestMapping("/homepage")
    public String homepage() {
        return "homepage";
    }
}
