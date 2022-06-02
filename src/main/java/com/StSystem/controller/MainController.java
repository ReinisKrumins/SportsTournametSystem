package com.StSystem.controller;

import com.StSystem.entity.Admin;
import com.StSystem.entity.BasketballMatch;
import com.StSystem.entity.FootballMatch;
import com.StSystem.entity.UserMessages;
import com.StSystem.service.BasketballMatchService;
import com.StSystem.service.FootballMatchsService;
import com.StSystem.service.UserMessagesService;
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

    @Autowired
    UserMessagesService userMessagesService;

    @GetMapping("/")
    public String getHome(Model model){
        footballMatchsService.fetctMatchs();
        model.addAttribute("footballMatches", footballMatchsService.readtopMatches());
        model.addAttribute("basketballMatches", basketballMatchService.getBasketballMatches());
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
    public String getContactPage(Model model){
        UserMessages user = new UserMessages();
        model.addAttribute("user", user);
        return "contact";
    }

    @PostMapping("/contact")
    public String submitForm(@ModelAttribute("user") UserMessages user) {
        userMessagesService.saveMessage(user);
        return "contact";
    }
    @RequestMapping("/login")
    public String adminLogin(Model model){
        Admin admin = new Admin();
        model.addAttribute("admin", admin);
        return "login";
    }

    @PostMapping("/login")
    public String validateAdmin(@ModelAttribute("admin") Admin admin, Model model) {
        System.out.println(admin.toString());
        if (admin.getUserName().equals("sid") && admin.getPassword().equals("1234footballadmin")) {
            return "wp-admin-1234footballadmin";
        } else {
            return "login";
        }
    }
    @RequestMapping("/wp-admin-1234footballadmin")
    public String wpAdmin(Model model){
        model.addAttribute("footballMatchs", footballMatchsService.readMatchs());
        return "wp-admin-1234footballadmin";
    }
    @GetMapping("/deletefootballmatch/{id}")
    public String deleteEmployee(@PathVariable (value = "id") int id, Model model) {

        // call delete employee method
        footballMatchsService.deleteFootballMatch(id);
        model.addAttribute("footballMatchs", footballMatchsService.readMatchs());
        return "redirect:/wp-admin-1234footballadmin";
    }
}
