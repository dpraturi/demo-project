package com.fissionlabs.demo_project.controller;

import com.fissionlabs.demo_project.dto.*;
import com.fissionlabs.demo_project.service.CustomQuestionResponseService;
import com.fissionlabs.demo_project.service.CustomQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class QuestionsController {

    private CustomQuestionService customQuestionService;

    private CustomQuestionResponseService customQuestionResponseService;

    @Autowired
    public QuestionsController(CustomQuestionService customQuestionService, CustomQuestionResponseService customQuestionResponseService) {
        this.customQuestionService = customQuestionService;
        this.customQuestionResponseService = customQuestionResponseService;
    }

    @GetMapping(path = "questions")
    public ResponseEntity<List<QuestionDTO>> getAllQuestions() {
        return ResponseEntity.ok(customQuestionService.getAllQuestions());
    }

    @PostMapping(path = "/create-questionnaire")
    public ResponseEntity<String> createQuestions(@RequestBody QuestionsRequestDTO questionsRequest) {

        if (questionsRequest == null || CollectionUtils.isEmpty(questionsRequest.getQuestions())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        boolean flag = customQuestionService.createQuestionnaires(questionsRequest.getQuestions());
        if (flag) {
            return ResponseEntity.ok("Questions Saved Successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping(path = "/questionnaire-responses")
    public ResponseEntity<String> getQuestionResponse(@RequestBody QuestionResponseDTO questionResponse) {

        if (questionResponse == null || CollectionUtils.isEmpty(questionResponse.getQuestionResponses())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<ResponseDTO> responseDTOList = questionResponse.getQuestionResponses().stream().map(InnerQuestResponseDTO::getResponse).collect(Collectors.toList());

        boolean flag = customQuestionResponseService.saveQuestionResponses(responseDTOList);
        if (flag) {
            return ResponseEntity.ok("Responses Saved Successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
