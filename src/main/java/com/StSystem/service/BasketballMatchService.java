package com.StSystem.service;

import com.StSystem.entity.BasketballMatch;
import com.StSystem.entity.FootballMatch;
import com.StSystem.repository.BasketballRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
@AllArgsConstructor
public class BasketballMatchService {

    @PersistenceContext
    private final EntityManager entityManager;
    @Autowired
    private final BasketballRepository basketballRepository;

    public List<BasketballMatch> getBasketballMatches(){
        return basketballRepository.findAll();
    }
    @Transactional
    public List<BasketballMatch> getLastMatchInTable(){

        List<BasketballMatch> lastMatchInTable = new ArrayList<>();
        for(BasketballMatch match: basketballRepository.findAll())
        {
            lastMatchInTable.add(match);
        }
        lastMatchInTable.remove(0);
        return lastMatchInTable;
    }
    public void createBasketballMatch(BasketballMatch basketballMatch){
        Optional<BasketballMatch> basketballMatchOptional = basketballRepository
                .findById(basketballMatch.getBasketballMatchId());
        if (basketballMatchOptional.isPresent()){
            throw new IllegalStateException("Cannot create new match");
        }
        basketballRepository.save(basketballMatch);
    }

    public void deleteBasketballMatch(Long basketballMatchId){
        boolean exists = basketballRepository.existsById(basketballMatchId);
        if(!exists){
            throw new IllegalStateException(
                    "Match with id " + basketballMatchId + " does not exist"
            );
        }
    }

    public boolean updateBasketballMatchById(Long basketballMathId, BasketballMatch basketballMatch){
        BasketballMatch newBasketballMatch = basketballRepository.findById(basketballMathId).get();
        if (newBasketballMatch != null){
            newBasketballMatch.setStartDate(basketballMatch.getStartDate());
            newBasketballMatch.setStartTime(basketballMatch.getStartTime());
            newBasketballMatch.setHomeTeam(basketballMatch.getHomeTeam());
            newBasketballMatch.setHomePts(basketballMatch.getHomePts());
            newBasketballMatch.setVisitorTeam(basketballMatch.getVisitorTeam());
            newBasketballMatch.setVisitorPts(basketballMatch.getVisitorPts());
            newBasketballMatch.setArena(basketballMatch.getArena());
            return true;
        }
        return false;
    }
    @Transactional
    public List<BasketballMatch> getUpcommingMatch(){

        List<BasketballMatch> upcommingMatchs = new ArrayList<>();
        for(BasketballMatch match: basketballRepository.findAll())
        {
            upcommingMatchs.add(match);
        }
        upcommingMatchs.remove(0);
        return upcommingMatchs;
    }
    @Transactional
    public void insertWithQuery(BasketballMatch basketballMatch){

        entityManager.createNativeQuery("INSERT INTO basketball (basketball_match_id, home_pts, home_team, start_date, start_time, visitor_pts, visitor_team, arena)"
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)")
                .setParameter(1, basketballMatch.getBasketballMatchId())
                .setParameter(2, basketballMatch.getHomePts())
                .setParameter(3, basketballMatch.getHomeTeam())
                .setParameter(4, basketballMatch.getStartDate())
                .setParameter(5, basketballMatch.getStartTime())
                .setParameter(6, basketballMatch.getVisitorPts())
                .setParameter(7, basketballMatch.getVisitorTeam())
                .setParameter(8, basketballMatch.getArena())
                .executeUpdate();
    }
    @Transactional
    public void clearOldData(){
        basketballRepository.clearTable();
    }
}
