package com.elarsenaldecamisetas.app.service.impl;

import com.elarsenaldecamisetas.app.dto.TeamDTO;
import com.elarsenaldecamisetas.app.entity.League;
import com.elarsenaldecamisetas.app.entity.Team;
import com.elarsenaldecamisetas.app.exception.ResourceNotFoundException;
import com.elarsenaldecamisetas.app.mapper.TeamMapper;
import com.elarsenaldecamisetas.app.repository.LeagueRepository;
import com.elarsenaldecamisetas.app.repository.TeamRepository;
import com.elarsenaldecamisetas.app.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final LeagueRepository leagueRepository;
    private final TeamMapper teamMapper;

    @Override
    @Transactional(readOnly = true)
    public List<TeamDTO> findAll() {
        return teamRepository.findAll().stream()
                .map(teamMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TeamDTO findById(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", id));
        return teamMapper.toDTO(team);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeamDTO> findByLeagueId(Long leagueId) {
        return teamRepository.findByLeagueId(leagueId).stream()
                .map(teamMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public TeamDTO create(TeamDTO teamDTO) {
        Team team = teamMapper.toEntity(teamDTO);
        League league = leagueRepository.findById(teamDTO.getLeagueId())
                .orElseThrow(() -> new ResourceNotFoundException("League", "id", teamDTO.getLeagueId()));
        team.setLeague(league);
        Team saved = teamRepository.save(team);
        return teamMapper.toDTO(saved);
    }

    @Override
    @Transactional
    public TeamDTO update(Long id, TeamDTO teamDTO) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", id));
        team.setName(teamDTO.getName());
        team.setImage(teamDTO.getImage());
        if (teamDTO.getLeagueId() != null) {
            League league = leagueRepository.findById(teamDTO.getLeagueId())
                    .orElseThrow(() -> new ResourceNotFoundException("League", "id", teamDTO.getLeagueId()));
            team.setLeague(league);
        }
        Team updated = teamRepository.save(team);
        return teamMapper.toDTO(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!teamRepository.existsById(id)) {
            throw new ResourceNotFoundException("Team", "id", id);
        }
        teamRepository.deleteById(id);
    }
}
