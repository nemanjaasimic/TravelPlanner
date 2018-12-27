package com.simic.travel_planner.model;

import com.simic.travel_planner.dto.RegisterDto;
import com.simic.travel_planner.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user database table.
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "USER_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int userId;

    @NotNull
    private String email;


    private Boolean emailVerified;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private Gender gender;

    @NotNull
    private String password;

    private String phoneNumber;

    @NotNull
    @Column(unique = true)
    private String username;

    @ManyToOne
    @JoinColumn(name="ROLE_ID")
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Trip> trips;

    public User(RegisterDto registerDto) {
        this.username = registerDto.getUsername();
        this.email = registerDto.getEmail();
        this.firstName = registerDto.getFirstName();
        this.lastName = registerDto.getLastName();
        this.gender = registerDto.getGender();
        this.phoneNumber = registerDto.getPhoneNumber();
        // this should be false if verification works
        this.emailVerified = true;
    }
}