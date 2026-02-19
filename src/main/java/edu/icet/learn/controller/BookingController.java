package edu.icet.learn.controller;

import edu.icet.learn.model.entity.Booking;
import edu.icet.learn.model.entity.Candidate;
import edu.icet.learn.service.InterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final InterviewService interviewService;

    @PostMapping("/book")
    public Booking bookInterview(@RequestParam Long slotId, @RequestBody Candidate candidate, @RequestParam String resumeLink) {
        try {
            return interviewService.bookInterview(candidate, slotId, resumeLink);
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to book interview: " + e.getMessage());
        }
    }

}
