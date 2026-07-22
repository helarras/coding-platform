package com.helarras.codingplatform.auth.internal;

import com.helarras.codingplatform.auth.LoginDto;
import com.helarras.codingplatform.auth.User;
import com.helarras.codingplatform.auth.RegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository repository;
    private final Mapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public void createUser(RegisterDto userDto) {
        User user = mapper.toUserEntity(userDto);
        user.setPasswordHash(passwordEncoder.encode(userDto.password()));
        repository.save(user);
    }


    public String verify(LoginDto loginDto) {
        var auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password()));
        return auth.isAuthenticated() ? jwtService.generateToken(loginDto.email()) : "Authentication failed!";
    }
}
