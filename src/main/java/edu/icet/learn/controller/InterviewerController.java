package edu.icet.learn.controller;

import edu.icet.learn.model.entity.Interviewer;
import edu.icet.learn.service.InterviewerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interviewers")
@RequiredArgsConstructor
public class InterviewerController {

    private final InterviewerService interviewerService;

    @PostMapping
    public Interviewer createInterviewer(@RequestBody Interviewer interviewer) {
        return interviewerService.saveInterviewer(interviewer);
    }

    @GetMapping
    public List<Interviewer> getAll() {
        return interviewerService.getAllInterviewers();
    }
}
