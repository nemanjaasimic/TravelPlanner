package com.simic.travel_planner.dto;

import com.simic.travel_planner.model.Gender;
import com.simic.travel_planner.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private int userId;

    private String email;

    private String firstName;

    private Gender gender;

    private String lastName;

    private String phoneNumber;

    private String username;

    private String roleName;

    public UserDto(User user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.gender = user.getGender();
        this.phoneNumber = user.getPhoneNumber();
        this.username = user.getUsername();
        this.roleName = user.getRole().getName();
    }
}
