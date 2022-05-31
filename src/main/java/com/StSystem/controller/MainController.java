package com.StSystem.controller;

import com.StSystem.entity.FootballMatch;
import com.StSystem.service.FootballMatchsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private FootballMatchsService footballMatchsService;

    @GetMapping("/")
    public String getHome(Model model){
        model.addAttribute("footballMatchs", footballMatchsService.readMatchs());
        return "index";
    }

    @RequestMapping("/football.html")
    public String getFootballMatches(Model model)
    {
        FootballMatch match = footballMatchsService.readMatchs().get(0);
        model.addAttribute("footballMatchs", footballMatchsService.readUpcomming());
        model.addAttribute("nextMatch", match);
        model.addAttribute("time", LocalTime.now().getHour()+":"+LocalTime.now().getMinute());
        return "football";
    }

    @RequestMapping("/basketball.html")
    public String getBaseballMatches() {
        return "basketball";
    }

    @RequestMapping("/volleyball.html")
    public String getVolleyballMatches() {
        return "volleyball";
    }

    @RequestMapping("/contact.html")
    public String getContactPage() {
        return "contact";
    }

    @RequestMapping("/wp-admin")
    public String adminLogin() {
        return "login";
    }
}
