package com.simic.travel_planner.controller;

import com.simic.travel_planner.dto.*;
import com.simic.travel_planner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginDto loginDto) {
        JwtAuthDto jwtToken = userService.login(loginDto);
        return ResponseEntity.ok(jwtToken);
    }

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterDto registerDto) {
        final UserDto user = userService.register(registerDto);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
