package com.StSystem.repository;


import com.StSystem.entity.VolleyballMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VolleyballRepository extends JpaRepository<VolleyballMatch, Integer> {
    public List<VolleyballMatch> findBydate(String date);

    @Query(value="select max(s.id) from volleyballmatch s", nativeQuery = true)
    public Integer findMaxId();

    public boolean existsBydate(String date);
}


