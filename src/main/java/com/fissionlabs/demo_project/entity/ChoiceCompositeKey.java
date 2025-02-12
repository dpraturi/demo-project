package com.fissionlabs.demo_project.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class ChoiceCompositeKey implements Serializable {

    private String optionId;
    private UUID questionId;

}
