package com.elarsenaldecamisetas.app.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.elarsenaldecamisetas.app.dto.LeagueDTO;
import com.elarsenaldecamisetas.app.entity.League;

@Component
public class LeagueMapper {
    private final ModelMapper mapper = new ModelMapper();

    public LeagueDTO toDTO(League league){
        return mapper.map(league, LeagueDTO.class);
    }

    public League toEntity(LeagueDTO leagueDTO){
        return mapper.map(leagueDTO, League.class);
    }
}
