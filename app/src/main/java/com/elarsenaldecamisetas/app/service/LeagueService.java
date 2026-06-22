package com.elarsenaldecamisetas.app.service;

import com.elarsenaldecamisetas.app.dto.LeagueDTO;

import java.util.List;

public interface LeagueService {
    List<LeagueDTO> findAll();
    LeagueDTO findById(Long id);
    LeagueDTO create(LeagueDTO leagueDTO);
    LeagueDTO update(Long id, LeagueDTO leagueDTO);
    void delete(Long id);
}
