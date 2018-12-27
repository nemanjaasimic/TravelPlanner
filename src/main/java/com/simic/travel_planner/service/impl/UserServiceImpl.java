package com.simic.travel_planner.service.impl;

import com.simic.travel_planner.dto.JwtAuthDto;
import com.simic.travel_planner.dto.LoginDto;
import com.simic.travel_planner.dto.RegisterDto;
import com.simic.travel_planner.dto.UserDto;
import com.simic.travel_planner.exception.ApiException;
import com.simic.travel_planner.exception.EmailAlreadyExistsException;
import com.simic.travel_planner.exception.UserNotFoundException;
import com.simic.travel_planner.exception.UsernameAlreadyExistsException;
import com.simic.travel_planner.model.Role;
import com.simic.travel_planner.model.User;
import com.simic.travel_planner.repository.RoleRepository;
import com.simic.travel_planner.repository.UserRepository;
import com.simic.travel_planner.security.JwtTokenProvider;
import com.simic.travel_planner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final JwtTokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JwtTokenProvider tokenProvider,
                           AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public JwtAuthDto login(LoginDto loginDto) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsernameOrEmail(),
                        loginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final User user = userRepository.findByUsernameOrEmail(loginDto.getUsernameOrEmail())
                .orElseThrow(() -> new UserNotFoundException("No user with given username or email found!"));
        final String jwt = tokenProvider.generateAuthToken(authentication);
        return new JwtAuthDto(jwt, user.getRole().getName());
    }

    @Override
    public UserDto register(RegisterDto registerDto) {
        if(userRepository.existsByUsername(registerDto.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        if(userRepository.existsByEmail(registerDto.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        // Finds role with name ROLE_USER
        // newly registered user has default USER role which has id 1
        final Role userRole = roleRepository.findById(1).orElseThrow(() -> new ApiException("User Role not set."));

        // Creating user's account
        final User user = new User(registerDto);
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRole(userRole);

        final User savedUser = userRepository.save(user);
        return new UserDto(savedUser);
    }
}
