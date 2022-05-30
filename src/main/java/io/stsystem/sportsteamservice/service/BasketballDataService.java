package io.stsystem.sportsteamservice.service;

import io.stsystem.sportsteamservice.model.BasketballGameData;
import io.stsystem.sportsteamservice.repository.BasketballDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BasketballDataService {

    @Autowired
    private final BasketballDataRepository basketballDataRepository;

}
