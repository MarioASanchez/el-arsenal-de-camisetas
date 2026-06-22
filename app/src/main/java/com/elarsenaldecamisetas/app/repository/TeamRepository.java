package com.elarsenaldecamisetas.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elarsenaldecamisetas.app.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>{
    List <Team> findByLeagueId(Long leagueId);
}
