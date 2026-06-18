package com.ucr.reco.controller;


import com.ucr.reco.dto.ReservationDTO;
import com.ucr.reco.model.Reservation;
import com.ucr.reco.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")Origin
@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<Reservation>> getAll() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<Reservation>> getReservationsByEmail(@PathVariable String email) {
        List<Reservation> reservations = reservationService.getReservationsByUserEmail(email);
        if (reservations.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reservations);
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationDTO reservation) {
        Reservation createdReservation = reservationService.add(reservation);
        return ResponseEntity.status(201).body(createdReservation);
    }
}
