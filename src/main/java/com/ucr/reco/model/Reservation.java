package com.ucr.reco.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb-reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "space_id")
    private Space space;
    @ManyToOne
    @JoinColumn(name = "user_email")
    private User user;
    private LocalDateTime startDateReserved;
    private LocalDateTime endDateReserved;
    private Status status; //PENDING, CONFIRMED o CANCELED

    public Reservation() {
    }

    public Reservation(Integer id, Space space, User user, LocalDateTime startDateReserved, LocalDateTime endDateReserved, Status status) {
        this.id = id;
        this.space = space;
        this.user = user;
        this.startDateReserved = startDateReserved;
        this.endDateReserved = endDateReserved;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getStarDateReserved() {
        return startDateReserved;
    }

    public void setStarDateReserved(LocalDateTime starDateReserved) {
        this.startDateReserved = starDateReserved;
    }

    public LocalDateTime getEndDateReserved() {
        return endDateReserved;
    }

    public void setEndDateReserved(LocalDateTime endDateReserved) {
        this.endDateReserved = endDateReserved;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
