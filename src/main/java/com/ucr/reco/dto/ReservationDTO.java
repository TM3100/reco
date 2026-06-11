package com.ucr.reco.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ucr.reco.model.Status;

import java.time.LocalDateTime;

public class ReservationDTO {

    private String userEmail;
    private Integer idSpace;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime endDate;


    public ReservationDTO() {
    }

    public ReservationDTO(String userEmail, Integer idSpace, LocalDateTime startDate, LocalDateTime endDate) {
        this.userEmail = userEmail;
        this.idSpace = idSpace;
        this.startDate = startDate;
        this.endDate = endDate;

    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserMail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getIdSpace() {
        return idSpace;
    }

    public void setIdSpace(Integer idSpace) {
        this.idSpace = idSpace;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }


}
