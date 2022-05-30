package io.stsystem.sportsteamservice.controller;

import io.stsystem.sportsteamservice.service.BasketballDataService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class MainController {

    @Autowired
    private final BasketballDataService basketballDataService;

    @GetMapping("/")
    public String testFunction(){
        System.out.println("test");
        return "index";
    }
}
