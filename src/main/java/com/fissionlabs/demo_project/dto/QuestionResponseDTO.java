package com.fissionlabs.demo_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class QuestionResponseDTO implements Serializable {

    @JsonProperty("question-responses")
    List<InnerQuestResponseDTO> questionResponses;
}
