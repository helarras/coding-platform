package com.helarras.codingplatform.user;

import lombok.Builder;

@Builder
public record UserDto(
        String username,
        String email,
        String password
) {
}
