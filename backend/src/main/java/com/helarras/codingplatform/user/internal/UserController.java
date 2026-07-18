package com.helarras.codingplatform.user.internal;

import com.helarras.codingplatform.user.CustomUserDetails;
import com.helarras.codingplatform.user.User;
import com.helarras.codingplatform.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping
    public ResponseEntity<String> register(@RequestBody UserDto user) {
        userService.createUser(user);
        return ResponseEntity.ok("User created successfully");
    }

    @GetMapping("/me")
    public ResponseEntity<String> getCurrentUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        User fullEntity = userDetails.getUserEntity();

        return ResponseEntity.ok("Logged in as: " + fullEntity.getEmail() + " Database UUID: " + fullEntity.getId());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id) {
        return ResponseEntity.ok("Admin successfully deleted user with ID: " + id);
    }
}
