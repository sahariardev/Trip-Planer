package com.sahariar.TripPlanner.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sahariar.TripPlanner.Model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}
