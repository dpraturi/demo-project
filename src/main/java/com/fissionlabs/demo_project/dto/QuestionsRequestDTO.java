package com.fissionlabs.demo_project.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class QuestionsRequestDTO implements Serializable {
    private List<QuestionDTO> questions;
}
