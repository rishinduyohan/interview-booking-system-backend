package edu.icet.learn.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "interviewers")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Interviewer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String designation;

    @OneToMany(mappedBy = "interviewer", cascade = CascadeType.ALL)
    private List<InterviewSlot> slots;
}
