package com.helarras.codingplatform.user.internal;

import com.helarras.codingplatform.user.User;
import com.helarras.codingplatform.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository repository;
    private final Mapper mapper;
    private final PasswordEncoder passwordEncoder;

    public void createUser(UserDto userDto) {
        User user = mapper.toUserEntity(userDto);
        user.setPasswordHash(passwordEncoder.encode(userDto.password()));
        repository.save(user);
    }
}
