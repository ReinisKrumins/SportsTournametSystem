package io.stsystem.sportsteamservice.service;

import io.stsystem.sportsteamservice.model.BasketballGameData;
import io.stsystem.sportsteamservice.repository.GameDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GameDataService {

    @Autowired
    private final GameDataRepository gameDataRepository;


}
