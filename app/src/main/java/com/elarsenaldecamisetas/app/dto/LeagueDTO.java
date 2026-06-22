package com.elarsenaldecamisetas.app.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeagueDTO {
    private Long id;
    private String name;
    private String actualImage;
    private String retroImage;
    private String banner;
    private List <TeamDTO> teams;
}
