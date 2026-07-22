package com.helarras.codingplatform.auth;

import lombok.Builder;

@Builder
public record RegisterDto(
        String username,
        String email,
        String password
) {
}
