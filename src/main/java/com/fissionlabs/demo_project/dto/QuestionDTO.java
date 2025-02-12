package com.fissionlabs.demo_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class QuestionDTO implements Serializable {
    private String questionId;
    @JsonProperty("is_required")
    private boolean isRequired;
    private String question;
    private String type;
    @JsonProperty("choices")
    private List<ChoiceDTO> choices;
}
