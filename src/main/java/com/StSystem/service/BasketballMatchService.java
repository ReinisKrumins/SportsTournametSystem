package com.StSystem.service;

import com.StSystem.repository.BasketballRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BasketballMatchService {

    @Autowired
    private final BasketballRepository basketballRepository;

}
