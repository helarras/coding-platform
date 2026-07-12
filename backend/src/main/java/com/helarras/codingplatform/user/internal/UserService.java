package com.helarras.codingplatform.user.internal;

import com.helarras.codingplatform.user.User;
import com.helarras.codingplatform.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository repository;
    private final Mapper mapper;

    public void createUser(UserDto user) {
        repository.save(mapper.toUserEntity(user));
    }
}
