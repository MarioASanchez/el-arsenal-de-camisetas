package com.elarsenaldecamisetas.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elarsenaldecamisetas.app.entity.League;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {
    
}
