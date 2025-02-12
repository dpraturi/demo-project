package com.fissionlabs.demo_project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class CustomQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "is_required")
    private Boolean isRequired;

    private String question;

    @Column(name = "choice_id")
    private String choices;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

//    private User createdBy;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "tenant_id")
    private String tenantId;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

//    private User updatedBy;

    public enum QuestionType {
        TEXT,
        SINGLE_SELECT,
        MULTI_SELECT
    }

    public enum Status {
        ACTIVE,
        DELETED
    }

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }
}
