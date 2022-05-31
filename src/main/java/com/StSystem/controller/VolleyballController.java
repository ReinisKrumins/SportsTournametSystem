package com.StSystem.controller;


import com.StSystem.entity.VolleyballMatch;
import com.StSystem.service.VolleyballMatchesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VolleyballController {

    @Autowired
    private VolleyballMatchesService volleyballMatchesService;

    @RequestMapping
    public String info() {
        return "The application is up...";
    }

    @RequestMapping("/matches")
    public String getUpdates(Model model) {
        model.addAttribute("volleyballMatches", volleyballMatchesService.readMatches());
        return "Volleyball Matches";
    }

    @RequestMapping(value = "createVolleyballMatch", method = RequestMethod.POST)
    public String createMatch(@RequestBody VolleyballMatch volleyballMatch) {
        return volleyballMatchesService.createVolleyballMatch(volleyballMatch);
    }

    @RequestMapping(value = "readVolleyballMatches", method = RequestMethod.GET)
    public List<VolleyballMatch> readMatches() {
        return volleyballMatchesService.readMatches();
    }

    @RequestMapping(value = "updateVolleyballMatch", method = RequestMethod.PUT)
    public String updateMatch(@RequestBody VolleyballMatch volleyballMatch) {
        return volleyballMatchesService.updateVolleyballMatch(volleyballMatch);
    }

    @RequestMapping(value = "deleteVolleyballMatch", method = RequestMethod.DELETE)
    public String deleteMatch(@RequestBody VolleyballMatch volleyballMatch) {
        return volleyballMatchesService.deleteVolleyballMatch(volleyballMatch);
    }
}

