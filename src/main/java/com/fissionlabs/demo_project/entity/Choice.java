package com.fissionlabs.demo_project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@IdClass(ChoiceCompositeKey.class)
public class Choice {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "option_id")
    private String optionId;

    @Id
    @Column(name = "question_id")
    private UUID questionId;

    @Column(name = "option_text")
    private String optionText;
}
