package com.StSystem.service;


import com.StSystem.entity.VolleyballMatch;
import com.StSystem.repository.VolleyballRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Service

public class VolleyballMatchesService {

    @Autowired
    private VolleyballRepository volleyballRepository;
    @Transactional
    public String createVolleyballMatch(VolleyballMatch volleyballMatch){
    try {
        if (!volleyballRepository.existsBydate(volleyballMatch.getDate())){
            volleyballMatch.setId(null == volleyballRepository.findMaxId()? 0 : volleyballRepository.findMaxId() + 1 );
            volleyballRepository.save(volleyballMatch);
            return "Match record created successfully.";
        } else {
            return "Match already exists in the database.";
        }}
        catch (Exception e) {
            throw e;
        }
    }

    public List<VolleyballMatch> readMatches(){
        return (List<VolleyballMatch>) volleyballRepository.findAll();
    }

    @Transactional
    public String updateVolleyballMatch(VolleyballMatch volleyballMatch){
        if (volleyballRepository.existsBydate(volleyballMatch.getDate())){
            try {
                List<VolleyballMatch> volleyballMatches = volleyballRepository.findBydate(volleyballMatch.getDate());
                volleyballMatches.stream().forEach(s -> {
                    VolleyballMatch volleyballMatchToBeUpdate = volleyballRepository.findById(s.getId()).get();
                    volleyballMatchToBeUpdate.setTeamA(volleyballMatch.getTeamA());
                    volleyballMatch.setTeamB(volleyballMatch.getTeamB());
                    volleyballMatchToBeUpdate.setDate(volleyballMatch.getDate());
                    volleyballMatchToBeUpdate.setTime(volleyballMatch.getTime());
                    volleyballRepository.save(volleyballMatchToBeUpdate);
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
    public String deleteVolleyballMatch(VolleyballMatch volleyballMatch){
        if (volleyballRepository.existsBydate(volleyballMatch.getDate())){
            try {
                List<VolleyballMatch> volleyballMatches = volleyballRepository.findBydate(volleyballMatch.getDate());
                volleyballMatches.stream().forEach(s -> {
                    volleyballRepository.delete(s);
                });
                return "Match record deleted successfully.";
            }catch (Exception e){
                throw e;
            }

        }else {
            return "Match does not exist";
        }
    }
}
