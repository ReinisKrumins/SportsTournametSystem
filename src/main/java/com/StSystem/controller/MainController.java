package com.StSystem.controller;

import com.StSystem.entity.BasketballMatch;
import com.StSystem.entity.FootballMatch;
import com.StSystem.service.BasketballMatchService;
import com.StSystem.service.FootballMatchsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.List;

@Controller
@AllArgsConstructor
public class MainController {

    @Autowired
    private final FootballMatchsService footballMatchsService;

    @Autowired
    private final BasketballMatchService basketballMatchService;

    @GetMapping("/")
    public String getHome(Model model){
        model.addAttribute("footballMatches", footballMatchsService.readMatchs());
        model.addAttribute("basketballMatches", basketballMatchService.getBasketballMatches());
        return "index";
    }

    @RequestMapping("/football.html")
    public String getFootballMatches(Model model)
    {
        FootballMatch match = footballMatchsService.readMatchs().get(0);
        model.addAttribute("footballMatches", footballMatchsService.readUpcomming());
        model.addAttribute("nextMatch", match);
        model.addAttribute("time", LocalTime.now().getHour()+":"+LocalTime.now().getMinute());
        return "football";
    }

    @RequestMapping("/basketball.html")
    public String getBasketballMatches(Model model) {
        BasketballMatch basketballMatch = basketballMatchService.getBasketballMatches().get(0);
        model.addAttribute("basketballMatches", basketballMatchService.getLastMatchInTable());
        model.addAttribute("nextBasketballMatch", basketballMatch);
        model.addAttribute("time", LocalTime.now().getHour()+":"+LocalTime.now().getMinute());
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
