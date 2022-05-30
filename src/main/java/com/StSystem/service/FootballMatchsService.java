package com.StSystem.service;

import com.StSystem.entity.FootballMatch;
import com.StSystem.repository.FootballRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FootballMatchsService {

    @Autowired
    private FootballRepository footballRepository;
    @Transactional
    public String createFootballMatch(FootballMatch footballMatch){
        try {
            if (!footballRepository.existsByDate(footballMatch.getDate())){
                footballMatch.setId(null == footballRepository.findMaxId()? 0 : footballRepository.findMaxId() + 1);
                footballRepository.save(footballMatch);
                return "Match record created successfully.";
            }else {
                return "Match already exists in the database.";
            }
        }catch (Exception e){
            throw e;
        }
    }

    public List<FootballMatch> readMatchs(){
        return footballRepository.findAll();
    }

    @Transactional
    public String updateFootballMatch(FootballMatch footballMatch){
        if (footballRepository.existsByDate(footballMatch.getDate())){
            try {
                List<FootballMatch> footballMatches = footballRepository.findByDate(footballMatch.getDate());
                footballMatches.stream().forEach(s -> {
                    FootballMatch footballMatchToBeUpdate = footballRepository.findById(s.getId()).get();
                    footballMatchToBeUpdate.setTeamA(footballMatch.getTeamA());
                    footballMatchToBeUpdate.setTeamB(footballMatch.getTeamB());
                    footballMatchToBeUpdate.setDate(footballMatch.getDate());
                    footballMatchToBeUpdate.setTime(footballMatch.getTime());
                    footballRepository.save(footballMatchToBeUpdate);
                });
                return "Match record updated.";
            }catch (Exception e){
                throw e;
            }
        }else {
            return "Match does not exists in the database.";
        }
    }

    @Transactional
    public String deleteFootballMatch(FootballMatch footballMatch){
        if (footballRepository.existsByDate(footballMatch.getDate())){
            try {
                List<FootballMatch> footballMatches = footballRepository.findByDate(footballMatch.getDate());
                footballMatches.stream().forEach(s -> {
                    footballRepository.delete(s);
                });
                return "Match record deleted successfully.";
            }catch (Exception e){
                throw e;
            }

        }else {
            return "Match does not exist";
        }
    }

    @Transactional
    public List<FootballMatch> readUpcomming(){

        List<FootballMatch> upcommingMatchs = new ArrayList<>();
        for(FootballMatch match: footballRepository.findAll())
        {
            upcommingMatchs.add(match);
        }
        upcommingMatchs.remove(0);
        return upcommingMatchs;
    }

}