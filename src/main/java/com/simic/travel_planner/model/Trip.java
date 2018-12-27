package com.simic.travel_planner.model;

import com.simic.travel_planner.dto.TripDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TRIP")
public class Trip implements Serializable {

    @Id
    @NotNull
    @Column(name = "TRIP_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int tripId;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    private String comment;

    @NotNull
    private String destination;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @NotNull
    private User user;

    public Trip(TripDto tripDto) {
        this.tripId = tripDto.getTripId();
        this.startDate = tripDto.getStartDate();
        this.endDate = tripDto.getEndDate();
        this.destination = tripDto.getDestination();
        this.comment = tripDto.getComment();
        this.user = new User();
        this.user.setUserId(tripDto.getUserId());
    }
}
