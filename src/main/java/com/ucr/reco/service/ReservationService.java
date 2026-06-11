package com.ucr.reco.service;


import com.ucr.reco.dto.ReservationDTO;
import com.ucr.reco.model.Reservation;
import com.ucr.reco.model.Space;
import com.ucr.reco.model.Status;
import com.ucr.reco.model.User;
import com.ucr.reco.repository.ReservationJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationJpaRepository reservationRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private SpaceService spaceService;

    public Reservation add(ReservationDTO reservation) {
        System.out.println("Pasó por aquí, email " + reservation.getUserEmail() + " id space " + reservation.getIdSpace());
        User user = userService.findByEmail(reservation.getUserEmail());
        Space space = spaceService.findById(reservation.getIdSpace());
        Reservation reservationTemp = new Reservation();

        reservationTemp.setStarDateReserved(reservation.getStartDate());
        reservationTemp.setEndDateReserved(reservation.getEndDate());

        reservationTemp.setUser(user);
        reservationTemp.setSpace(space);
        reservationTemp.setUser(user);
        reservationTemp.setSpace(space);
        return reservationRepository.save(reservationTemp);
    }

    public Reservation create(ReservationDTO reservationDTO) {

        if (reservationDTO.getStartDate() == null || reservationDTO.getEndDate() == null) {
            throw new RuntimeException("Las fechas son obligatorias");
        }

        if (reservationDTO.getStartDate().isAfter(reservationDTO.getEndDate())) {
            throw new RuntimeException("La fecha inicial no puede ser mayor a la final");
        }

        if (reservationDTO.getStartDate().isEqual(reservationDTO.getEndDate())) {
            throw new RuntimeException("La reserva debe tener una duración válida");
        }

        if (reservationDTO.getStartDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("No se permiten reservas en fechas pasadas");
        }

        User user = userService.findByEmail(reservationDTO.getUserEmail());

        if (user == null) {
            throw new RuntimeException("El usuario no existe");
        }

        Space space = spaceService.findById(reservationDTO.getIdSpace());

        if (space == null) {
            throw new RuntimeException("El espacio no existe");
        }

        List<Reservation> conflicts = reservationRepository.findConflictingReservations(
                reservationDTO.getIdSpace(),
                reservationDTO.getStartDate(),
                reservationDTO.getEndDate()
        );

        if (!conflicts.isEmpty()) {
            throw new RuntimeException("El espacio ya se encuentra reservado");
        }

        Reservation reservation = new Reservation();

        reservation.setStarDateReserved(reservationDTO.getStartDate());
        reservation.setEndDateReserved(reservationDTO.getEndDate());

        reservation.setStatus(Status.PENDING);

        reservation.setUser(user);
        reservation.setSpace(space);

        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public List<Reservation> getReservationsByUserEmail(String email) {
        User user = userService.findByEmail(email);
        return reservationRepository.findByUserEmail(user);
    }
}
