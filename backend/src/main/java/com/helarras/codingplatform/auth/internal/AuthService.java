package com.helarras.codingplatform.auth.internal;

import com.helarras.codingplatform.auth.User;
import com.helarras.codingplatform.auth.registerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository repository;
    private final Mapper mapper;
    private final PasswordEncoder passwordEncoder;

    public void createUser(registerDto userDto) {
        User user = mapper.toUserEntity(userDto);
        user.setPasswordHash(passwordEncoder.encode(userDto.password()));
        repository.save(user);
    }
}
