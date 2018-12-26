package com.simic.travel_planner.service;

import com.simic.travel_planner.dto.JwtAuthDto;
import com.simic.travel_planner.dto.LoginDto;
import com.simic.travel_planner.dto.RegisterDto;
import com.simic.travel_planner.dto.UserDto;

public interface UserService {
    JwtAuthDto login(LoginDto loginDto);

    UserDto register(RegisterDto registerDto);
}
