package edu.icet.learn.service;

import edu.icet.learn.model.entity.Interviewer;
import edu.icet.learn.repository.InterviewerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterviewerService {
    public final InterviewerRepository interviewerRepository;

    public Interviewer saveInterviewer(Interviewer interviewer) {
        return interviewerRepository.save(interviewer);
    }

    public List<Interviewer> getAllInterviewers() {
        return interviewerRepository.findAll();
    }
}
