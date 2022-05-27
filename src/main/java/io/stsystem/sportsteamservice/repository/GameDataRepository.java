package io.stsystem.sportsteamservice.repository;

import io.stsystem.sportsteamservice.model.BasketballGameData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameDataRepository extends JpaRepository<BasketballGameData, Long> {

}
