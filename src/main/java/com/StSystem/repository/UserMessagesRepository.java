package com.StSystem.repository;

import com.StSystem.entity.FootballMatch;
import com.StSystem.entity.UserMessages;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserMessagesRepository extends JpaRepository<UserMessages, Integer> {

    public boolean existsByName(String name);
    public boolean existsByEmail(String email);
    public boolean existsBySubject(String subject);
    public boolean existsByMessage(String message);
    public List<UserMessages> findByEmail(String date);


    @Query("select max(s.id) from FootballMatch s")
    public Integer findMaxId();
}
