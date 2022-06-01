package com.StSystem.repository;

import com.StSystem.entity.BasketballMatch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BasketballRepository extends JpaRepository<BasketballMatch, Long> {

    @Modifying
    @Query(value = "TRUNCATE TABLE basketball", nativeQuery = true)
    void clearTable();

}
