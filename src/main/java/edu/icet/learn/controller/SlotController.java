package edu.icet.learn.controller;

import edu.icet.learn.model.entity.InterviewSlot;
import edu.icet.learn.service.InterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slots")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class SlotController {

    private final InterviewService interviewService;

    @GetMapping("/available")
    public List<InterviewSlot> getAvailableSlots() {
        return interviewService.getAvailableSlots();
    }

    @PostMapping("/create")
    public List<InterviewSlot> createSlot(@RequestBody InterviewSlot slot) {
        try {
            return interviewService.createSlot(slot);
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed : " + e.getMessage());

        }
    }
}
