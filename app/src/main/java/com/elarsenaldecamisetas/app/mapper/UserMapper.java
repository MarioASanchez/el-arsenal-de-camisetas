package com.elarsenaldecamisetas.app.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.elarsenaldecamisetas.app.dto.UserDTO;
import com.elarsenaldecamisetas.app.entity.User;

@Component
public class UserMapper {
    private final ModelMapper mapper = new ModelMapper();

    public UserDTO toDTO(User user){
        return mapper.map(user, UserDTO.class);
    }

    public User toEntity(UserDTO userDTO){
        return mapper.map(userDTO, User.class);
    }
}
