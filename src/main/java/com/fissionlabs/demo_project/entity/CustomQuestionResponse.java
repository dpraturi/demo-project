package com.fissionlabs.demo_project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class CustomQuestionResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

//    @ManyToOne
//    @JoinColumn(name = "question_id")
//    private CustomQuestion question;
    @Column(name = "question_id")
    private String questionId;

    @Column(name = "response_text")
    private String responseText;

//    @ManyToOne
//    @JoinColumn(name = "response_id")
//    private Choice responseChoice;
    @JoinColumn(name = "response_id")
    private String responseId;

    @Column(name = "tenant_id")
    private String tenantId;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

}
