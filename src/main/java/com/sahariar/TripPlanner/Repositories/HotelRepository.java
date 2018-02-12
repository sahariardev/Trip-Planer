package com.sahariar.TripPlanner.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sahariar.TripPlanner.Model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
