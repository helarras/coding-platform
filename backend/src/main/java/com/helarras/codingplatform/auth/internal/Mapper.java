package com.helarras.codingplatform.auth.internal;

import com.helarras.codingplatform.auth.User;
import com.helarras.codingplatform.auth.RegisterDto;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public User toUserEntity(RegisterDto userDto) {
        return User.builder()
                .username(userDto.username())
                .email(userDto.email())
                .passwordHash(userDto.password())
                .build();
    }
}
