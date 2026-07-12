package com.helarras.codingplatform.user.internal;

import com.helarras.codingplatform.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDto user) {
        userService.createUser(user);
        return ResponseEntity.ok("User created successfully");
    }
}
