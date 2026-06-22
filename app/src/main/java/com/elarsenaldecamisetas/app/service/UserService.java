package com.elarsenaldecamisetas.app.service;

import com.elarsenaldecamisetas.app.dto.ChangePasswordRequest;
import com.elarsenaldecamisetas.app.dto.UserDTO;

import java.util.List;

public interface UserService {
    public UserDTO findById(Long id);
    public UserDTO findByEmail(String email);
    public UserDTO getCurrentUser();
    public UserDTO updateProfile(UserDTO userDTO);
    public void changePassword(ChangePasswordRequest request);
    public List<UserDTO> findAll();
    public void delete(Long id);
}
