package com.elarsenaldecamisetas.app.service;

import com.elarsenaldecamisetas.app.dto.TeamDTO;

import java.util.List;

public interface TeamService {
    List<TeamDTO> findAll();
    TeamDTO findById(Long id);
    List<TeamDTO> findByLeagueId(Long leagueId);
    TeamDTO create(TeamDTO teamDTO);
    TeamDTO update(Long id, TeamDTO teamDTO);
    void delete(Long id);
}
