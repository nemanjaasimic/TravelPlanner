package com.simic.travel_planner.controller;

import com.simic.travel_planner.dto.TripDto;
import com.simic.travel_planner.security.UserPrincipal;
import com.simic.travel_planner.service.ReportService;
import com.simic.travel_planner.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping(path = "/trips")
public class TripController {

    private final TripService tripService;

    private final ReportService reportService;

    @Autowired
    public TripController(TripService tripService, ReportService reportService) {
        this.tripService = tripService;
        this.reportService = reportService;
    }

    @GetMapping(produces = "application/json; charset=UTF-8")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<TripDto>> getAllTrips() {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(tripService.getAllTrips(principal.getId()));
    }

    @GetMapping(path = "/{id}", produces = "application/json; charset=UTF-8")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<TripDto> getTripById(@PathVariable("id") int id) {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(tripService.getTripById(principal.getId(), id));
    }

    @GetMapping(path = "/filter", produces = "application/json; charset=UTF-8")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<TripDto>> getTripsFiltered(@RequestParam(value = "dest") String destination,
                                                          @RequestParam(value = "comment", required = false) String comment) {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(tripService.getAllTripsFiltered(principal.getId(), destination, comment));
    }

    @PostMapping(consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<TripDto> createTrip(@Valid @RequestBody TripDto tripDto) {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(tripService.createTrip(principal.getId(), tripDto));
    }

    @PutMapping(path = "/{id}", consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<TripDto> updateTrip(@PathVariable("id") int id, @Valid @RequestBody TripDto tripDto) {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(tripService.updateTrip(principal.getId(), id, tripDto));
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> deleteTrip(@PathVariable("id") int id) {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(tripService.deleteTrip(principal.getId(), id)) {
            return ResponseEntity.ok("Successfully deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't delete trip.");
        }
    }

    @GetMapping(path = "/report", produces = MediaType.APPLICATION_PDF_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<InputStreamResource> getReport() {
        ByteArrayInputStream reportFileStream = reportService.getTripsReport();
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=report.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(reportFileStream));
    }
}
