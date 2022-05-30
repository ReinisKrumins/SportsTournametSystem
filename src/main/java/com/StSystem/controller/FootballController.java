package com.StSystem.controller;

import com.StSystem.entity.FootballMatch;
import com.StSystem.service.FootballMatchsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FootballController {

    @Autowired
    private FootballMatchsService footballMatchsService;
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public String info(){
        return "The application is up...";
    }


    @RequestMapping(value = "createfootballmatch", method = RequestMethod.POST)
    public String createStudent(@RequestBody FootballMatch footballMatch){
        return footballMatchsService.createFootballMatch(footballMatch);
    }

    @RequestMapping(value = "readfootballmatchs", method = RequestMethod.GET)
    public List<FootballMatch> readStudents(){
        return footballMatchsService.readMatchs();
    }

    @RequestMapping(value = "updatefootballmatch", method = RequestMethod.PUT)
    public String updateMatch(@RequestBody FootballMatch footballMatch){
        return footballMatchsService.updateFootballMatch(footballMatch);
    }

    @RequestMapping(value = "deletefootballmatch", method = RequestMethod.DELETE)
    public String deleteMatch(@RequestBody FootballMatch footballMatch){
        return footballMatchsService.deleteFootballMatch(footballMatch);
    }
}
