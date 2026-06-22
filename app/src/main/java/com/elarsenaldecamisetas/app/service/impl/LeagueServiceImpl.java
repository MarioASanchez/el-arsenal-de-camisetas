package com.elarsenaldecamisetas.app.service.impl;

import com.elarsenaldecamisetas.app.dto.LeagueDTO;
import com.elarsenaldecamisetas.app.entity.League;
import com.elarsenaldecamisetas.app.exception.ResourceNotFoundException;
import com.elarsenaldecamisetas.app.mapper.LeagueMapper;
import com.elarsenaldecamisetas.app.repository.LeagueRepository;
import com.elarsenaldecamisetas.app.service.LeagueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeagueServiceImpl implements LeagueService {

    private final LeagueRepository leagueRepository;
    private final LeagueMapper leagueMapper;

    @Override
    @Transactional(readOnly = true)
    public List<LeagueDTO> findAll() {
        return leagueRepository.findAll().stream()
                .map(leagueMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public LeagueDTO findById(Long id) {
        League league = leagueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("League", "id", id));
        return leagueMapper.toDTO(league);
    }

    @Override
    @Transactional
    public LeagueDTO create(LeagueDTO leagueDTO) {
        League league = leagueMapper.toEntity(leagueDTO);
        League saved = leagueRepository.save(league);
        return leagueMapper.toDTO(saved);
    }

    @Override
    @Transactional
    public LeagueDTO update(Long id, LeagueDTO leagueDTO) {
        League league = leagueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("League", "id", id));
        league.setName(leagueDTO.getName());
        league.setActualImage(leagueDTO.getActualImage());
        league.setRetroImage(leagueDTO.getRetroImage());
        league.setBanner(leagueDTO.getBanner());
        League updated = leagueRepository.save(league);
        return leagueMapper.toDTO(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!leagueRepository.existsById(id)) {
            throw new ResourceNotFoundException("League", "id", id);
        }
        leagueRepository.deleteById(id);
    }
}
