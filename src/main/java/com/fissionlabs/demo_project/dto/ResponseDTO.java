package com.fissionlabs.demo_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseDTO implements Serializable {

    @JsonProperty("question_id")
    private String questionId;

    @JsonProperty("text")
    private String responseText;

    @JsonProperty("option_id")
    private String optionId;
}
