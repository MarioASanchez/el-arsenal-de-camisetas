package com.elarsenaldecamisetas.app.service;

import com.elarsenaldecamisetas.app.dto.AuthResponse;
import com.elarsenaldecamisetas.app.dto.LoginRequest;
import com.elarsenaldecamisetas.app.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
