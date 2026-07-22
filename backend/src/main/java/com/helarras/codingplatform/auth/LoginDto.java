package com.helarras.codingplatform.auth;

import lombok.Builder;

@Builder
public record LoginDto(
        String email,
        String password
) {
}
