package com.elarsenaldecamisetas.app.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elarsenaldecamisetas.app.config.security.JwtTokenProvider;
import com.elarsenaldecamisetas.app.dto.AuthResponse;
import com.elarsenaldecamisetas.app.dto.LoginRequest;
import com.elarsenaldecamisetas.app.dto.RegisterRequest;
import com.elarsenaldecamisetas.app.entity.User;
import com.elarsenaldecamisetas.app.enums.Role;
import com.elarsenaldecamisetas.app.exception.BadRequestException;
import com.elarsenaldecamisetas.app.mapper.UserMapper;
import com.elarsenaldecamisetas.app.repository.UserRepository;
import com.elarsenaldecamisetas.app.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException("Email already in use");
        }

        User user = new User();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.CLIENTE);
        user.setAddress(request.getAddress());
        user.setCity(request.getCity());
        user.setCp(request.getCp());
        user.setCountry(request.getCountry());
        user.setPhoneNumber(request.getPhoneNumber());

        userRepository.save(user);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        String token = jwtTokenProvider.generateToken(authentication);

        return new AuthResponse(token, userMapper.toDTO(user));
    }

    @Override
    @Transactional
    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        String token = jwtTokenProvider.generateToken(authentication);

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid credentials"));

        return new AuthResponse(token, userMapper.toDTO(user));
    }
}
