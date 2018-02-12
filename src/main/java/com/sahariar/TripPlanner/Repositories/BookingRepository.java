package com.sahariar.TripPlanner.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sahariar.TripPlanner.Model.Bookings;


@Repository
public interface BookingRepository extends JpaRepository<Bookings, Long> {

}
