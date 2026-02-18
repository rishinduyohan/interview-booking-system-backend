package edu.icet.learn.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings", uniqueConstraints = {@UniqueConstraint(columnNames = {"slot_id"})})
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    @OneToOne
    @JoinColumn(name = "slot_id", nullable = false)
    private InterviewSlot interviewSlot;

    private String status = "CONFIRMED"; // PENDING, CONFIRMED, CANCELLED

    private LocalDateTime createdAt = LocalDateTime.now();
}
