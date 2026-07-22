package com.helarras.codingplatform.auth.internal;

import com.helarras.codingplatform.auth.LoginDto;
import com.helarras.codingplatform.auth.UserPrincipal;
import com.helarras.codingplatform.auth.User;
import com.helarras.codingplatform.auth.RegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto user) {
        authService.createUser(user);
        return ResponseEntity.ok("User created successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        System.out.println(loginDto);
        return ResponseEntity.ok(authService.verify(loginDto));
    }

    @GetMapping("/me")
    public ResponseEntity<String> getCurrentUser(@AuthenticationPrincipal UserPrincipal userDetails) {
        User fullEntity = userDetails.getUserEntity();

        return ResponseEntity.ok("Logged in as: " + fullEntity.getEmail() + " Database UUID: " + fullEntity.getId());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id) {
        return ResponseEntity.ok("Admin successfully deleted user with ID: " + id);
    }
}
