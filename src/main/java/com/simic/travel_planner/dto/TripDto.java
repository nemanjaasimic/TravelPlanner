package com.simic.travel_planner.dto;

import com.simic.travel_planner.model.Trip;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripDto {

    @NotNull
    private int tripId;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    private String comment;

    @NotNull
    private String destination;

    @NotNull
    private int userId;

    private long daysToStart;

    public TripDto(Trip trip) {
        this.tripId = trip.getTripId();
        this.startDate = trip.getStartDate();
        this.endDate = trip.getEndDate();
        this.destination = trip.getDestination();
        this.comment = trip.getComment();
        this.userId = trip.getUser().getUserId();

        if(trip.getStartDate().after(DateTime.now().toDate())) {
            long timeLeft = startDate.getTime() - DateTime.now().toDate().getTime();
            this.daysToStart = TimeUnit.DAYS.convert(timeLeft, TimeUnit.MILLISECONDS);
        } else {
            daysToStart = -1;
        }
    }

}
