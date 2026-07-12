package com.helarras.codingplatform.user.internal;

import com.helarras.codingplatform.user.User;
import com.helarras.codingplatform.user.UserDto;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public User toUserEntity(UserDto userDto) {
        return User.builder()
                .username(userDto.username())
                .email(userDto.email())
                .passwordHash(userDto.password())
                .build();
    }
}
