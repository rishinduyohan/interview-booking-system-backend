package edu.icet.learn.config;

import edu.icet.learn.model.entity.InterviewSlot;
import edu.icet.learn.model.entity.Interviewer;
import edu.icet.learn.repository.InterviewerRepository;
import edu.icet.learn.service.InterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final InterviewerRepository interviewerRepository;
    private final InterviewService interviewService;

    @Override
    public void run(String... args) throws Exception {
        if (interviewerRepository.count() == 0) {
            Interviewer interviewer = new Interviewer();
            interviewer.setName("Alice Smith");
            interviewer.setEmail("alice@company.com");
            interviewer.setDesignation("Senior Software Engineer");
            interviewerRepository.save(interviewer);

            Interviewer hr = new Interviewer();
            hr.setName("Bob Johnson");
            hr.setEmail("bob@company.com");
            hr.setDesignation("HR Manager");
            interviewerRepository.save(hr);

            // Generate slots for today
            LocalDate today = LocalDate.now();
            LocalDateTime startToday = today.atTime(9, 0);
            LocalDateTime endToday = today.atTime(17, 0);

            InterviewSlot slot1 = new InterviewSlot();
            slot1.setInterviewer(interviewer);
            slot1.setStartTime(startToday);
            slot1.setEndTime(endToday);
            interviewService.createSlot(slot1);

            // Generate slots for tomorrow
            LocalDate tomorrow = today.plusDays(1);
            LocalDateTime startTomorrow = tomorrow.atTime(10, 0);
            LocalDateTime endTomorrow = tomorrow.atTime(15, 0);

            InterviewSlot slot2 = new InterviewSlot();
            slot2.setInterviewer(hr);
            slot2.setStartTime(startTomorrow);
            slot2.setEndTime(endTomorrow);
            interviewService.createSlot(slot2);
        }
    }
}
