package com.simic.travel_planner.controller;

import com.simic.travel_planner.dto.RegisterDto;
import com.simic.travel_planner.dto.UserDto;
import com.simic.travel_planner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasRole('USER_MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody RegisterDto registerDto) {
        return ResponseEntity.ok().body(userService.register(registerDto));
    }

}
