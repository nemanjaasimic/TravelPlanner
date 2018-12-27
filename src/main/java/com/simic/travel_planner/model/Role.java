package com.simic.travel_planner.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name="ROLE_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int roleId;

    @NotNull
    private String name;

    //bi-directional many-to-one association to User
    @OneToMany(mappedBy="role", fetch = FetchType.LAZY)
    private List<User> users;

}
