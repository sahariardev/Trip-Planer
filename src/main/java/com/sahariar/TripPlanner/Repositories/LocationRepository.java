package com.sahariar.TripPlanner.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sahariar.TripPlanner.Model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}
