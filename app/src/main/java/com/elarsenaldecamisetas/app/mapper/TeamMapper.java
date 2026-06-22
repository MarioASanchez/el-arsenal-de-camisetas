package com.elarsenaldecamisetas.app.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.elarsenaldecamisetas.app.dto.TeamDTO;
import com.elarsenaldecamisetas.app.entity.Team;

@Component
public class TeamMapper {
    private final ModelMapper mapper = new ModelMapper();

    public TeamMapper(){
        mapper.typeMap(Team.class, TeamDTO.class).addMappings(m -> {
            m.map(src -> src.getLeague().getId(), TeamDTO::setLeagueId);
        });
    }


    public TeamDTO toDTO(Team team){
        return mapper.map(team, TeamDTO.class);
    }

    public Team toEntity(TeamDTO teamDTO){
        return mapper.map(teamDTO, Team.class);
    }
}
