package com.sahariar.TripPlanner.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sahariar.TripPlanner.Model.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}
