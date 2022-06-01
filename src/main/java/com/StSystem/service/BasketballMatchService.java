package com.StSystem.service;

import com.StSystem.entity.BasketballMatch;
import com.StSystem.repository.BasketballRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BasketballMatchService {

    @Autowired
    private final BasketballRepository basketballRepository;

    public List<BasketballMatch> getBasketballMatches(){
        return basketballRepository.findAll();
    }

    @PersistenceContext
    EntityManager entityManager;
    @Transactional
    public void insertWithQuery(BasketballMatch basketballMatch){

        entityManager.createNativeQuery("INSERT INTO basketball (basketball_match_id, home_pts, home_team, start_date, start_time, visitor_pts, visitor_team)"
                        + "VALUES (?, ?, ?, ?, ?, ?, ?)")
                .setParameter(1, basketballMatch.getBasketballMatchId())
                .setParameter(2, basketballMatch.getHomePts())
                .setParameter(3, basketballMatch.getHomeTeam())
                .setParameter(4, basketballMatch.getStartDate())
                .setParameter(5, basketballMatch.getStartTime())
                .setParameter(6, basketballMatch.getVisitorPts())
                .setParameter(7, basketballMatch.getVisitorTeam())
                .executeUpdate();
    }
    @Transactional
    public void clearOldData(){
        basketballRepository.clearTable();
    }
}
