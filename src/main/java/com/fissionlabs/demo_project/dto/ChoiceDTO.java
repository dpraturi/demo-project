package com.fissionlabs.demo_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ChoiceDTO implements Serializable {

    @JsonProperty("option_id")
    private String optionId;

    @JsonProperty("option_text")
    private String optionText;
}
