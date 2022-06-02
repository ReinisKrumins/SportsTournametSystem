package com.StSystem.repository;

import com.StSystem.entity.BasketballMatch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BasketballRepository extends JpaRepository<BasketballMatch, Long> {

    @Query(value = "SELECT * FROM basketball ORDER BY basketball_match_id DESC LIMIT 1;", nativeQuery = true)
    List<BasketballMatch> getLastRow(Long basketballMatchId);
    @Modifying
    @Query(value = "TRUNCATE TABLE basketball", nativeQuery = true)
    void clearTable();

}
