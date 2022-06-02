package com.StSystem.controller;


import com.StSystem.entity.BasketballMatch;
import com.StSystem.service.BasketballMatchService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;


@RestController
@AllArgsConstructor
public class BasketballController {

    @Autowired
    private final BasketballMatchService basketballMatchService;


}
