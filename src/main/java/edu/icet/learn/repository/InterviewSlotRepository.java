package edu.icet.learn.repository;

import edu.icet.learn.model.entity.InterviewSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InterviewSlotRepository extends JpaRepository<InterviewSlot, Long> {

    @Query("SELECT s FROM InterviewSlot s WHERE s.interviewer.id = :interviewerId " +
            "AND (:start < s.endTime AND :end > s.startTime)")
    List<InterviewSlot> findOverlappingSlots(Long interviewerId, LocalDateTime start, LocalDateTime end);

}
