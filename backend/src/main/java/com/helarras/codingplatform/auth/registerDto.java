package com.helarras.codingplatform.auth;

import lombok.Builder;

@Builder
public record registerDto(
        String username,
        String email,
        String password
) {
}
