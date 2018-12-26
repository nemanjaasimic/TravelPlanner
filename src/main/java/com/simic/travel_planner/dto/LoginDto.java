package com.simic.travel_planner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    @NotBlank
    private String usernameOrEmail;

    @NotBlank
    private String password;

}
