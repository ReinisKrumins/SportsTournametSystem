package com.StSystem.controller;


import com.StSystem.entity.BasketballMatch;
import com.StSystem.service.BasketballMatchService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
public class BasketballController {

    @Autowired
    private final BasketballMatchService basketballMatchService;

    @GetMapping(value = "/test")
    public List<BasketballMatch> getBasketballMatches(){
        return basketballMatchService.getBasketballMatches();
    }

}
