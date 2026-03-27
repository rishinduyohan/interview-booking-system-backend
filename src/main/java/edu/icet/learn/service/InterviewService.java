package edu.icet.learn.service;

import edu.icet.learn.model.entity.Booking;
import edu.icet.learn.model.entity.Candidate;
import edu.icet.learn.model.entity.InterviewSlot;
import edu.icet.learn.model.entity.Interviewer;
import edu.icet.learn.repository.BookingRepository;
import edu.icet.learn.repository.InterviewSlotRepository;
import edu.icet.learn.repository.InterviewerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InterviewService {
    private final InterviewSlotRepository slotRepository;
    private final BookingRepository bookingRepository;
    private final InterviewerRepository interviewerRepository;
    private final edu.icet.learn.repository.CandidateRepository candidateRepository;

    @Transactional
    public java.util.List<InterviewSlot> createSlot(InterviewSlot slot) {

        Long interviewerId = slot.getInterviewer().getId();

        if (slot.getStartTime().isBefore(LocalDateTime.now())) {
            log.info("Time expired");
        }

        Interviewer interviewer = interviewerRepository.findById(interviewerId)
                .orElseThrow(() -> new RuntimeException("Interviewer not found with ID: " + interviewerId));

        List<InterviewSlot> overlaps = slotRepository.findOverlappingSlots(
                slot.getInterviewer().getId(), slot.getStartTime(), slot.getEndTime());

        if (!overlaps.isEmpty()) {
            log.info("Interviewer overlapped");
        }

        java.util.List<InterviewSlot> createdSlots = new java.util.ArrayList<>();
        LocalDateTime currentStart = slot.getStartTime();
        LocalDateTime finalEnd = slot.getEndTime();

        while (currentStart.isBefore(finalEnd)) {
            LocalDateTime nextEnd = currentStart.plusMinutes(30);
            if (nextEnd.isAfter(finalEnd)) {
                break; // Skip remaining uneven time
            }
            InterviewSlot newSlot = new InterviewSlot();
            newSlot.setInterviewer(interviewer);
            newSlot.setStartTime(currentStart);
            newSlot.setEndTime(nextEnd);
            newSlot.setAvailable(true);
            createdSlots.add(slotRepository.save(newSlot));
            currentStart = nextEnd;
        }

        return createdSlots;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<InterviewSlot> getAvailableSlots() {
        return slotRepository.findByIsAvailableTrue();
    }

    @Transactional
    public Booking bookInterview(Candidate candidate, Long slotId, String resumeLink) {
        InterviewSlot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found with id: " + slotId));

        if (!slot.isAvailable()) {
            throw new RuntimeException("Time is already booked");
        }

        Candidate existingCandidate = candidateRepository.findByEmail(candidate.getEmail()).orElse(null);
        if (existingCandidate != null) {
            existingCandidate.setPhoneNumber(candidate.getPhoneNumber());
            existingCandidate.setUsername(candidate.getUsername());
            candidate = candidateRepository.save(existingCandidate);
        } else {
            candidate = candidateRepository.save(candidate);
        }

        Booking booking = new Booking();
        booking.setCandidate(candidate);
        booking.setInterviewSlot(slot);
        booking.setResumeLink(resumeLink);
        slot.setAvailable(false);
        slotRepository.save(slot);

        Booking savedBooking = bookingRepository.save(booking);

        log.info("[EMAIL SIMULATION]: Confirmation sent to {} for interview at {}",
                candidate.getEmail(), slot.getStartTime());

        return savedBooking;
    }
}
