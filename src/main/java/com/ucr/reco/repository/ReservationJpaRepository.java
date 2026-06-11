package com.ucr.reco.repository;

import com.ucr.reco.model.Reservation;
import com.ucr.reco.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationJpaRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findByUserEmail(User user);

    @Query("""
            SELECT r FROM Reservation r
            WHERE r.space.id = :spaceId
            AND r.status <> Status.CANCELED
            AND (
                r.startDateReserved < :endDate
                AND r.endDateReserved > :startDate
            )
            """)
    List<Reservation> findConflictingReservations(
            @Param("spaceId") Integer spaceId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );
}
