package edu.icet.learn.repository;

import edu.icet.learn.model.entity.Interviewer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewerRepository extends JpaRepository<Interviewer,Long> {
}
