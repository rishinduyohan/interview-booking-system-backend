package edu.icet.learn.controller;

import edu.icet.learn.model.entity.InterviewSlot;
import edu.icet.learn.service.InterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/slots")
@RequiredArgsConstructor
public class SlotController {

    private final InterviewService interviewService;

    @PostMapping("/create")
    public InterviewSlot createSlot(@RequestBody InterviewSlot slot) {
        try {
            return interviewService.createSlot(slot);
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed : " + e.getMessage());

        }
    }
}
