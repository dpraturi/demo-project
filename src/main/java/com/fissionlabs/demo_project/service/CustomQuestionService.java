package com.fissionlabs.demo_project.service;

import com.fissionlabs.demo_project.dto.QuestionDTO;
import com.fissionlabs.demo_project.entity.Choice;
import com.fissionlabs.demo_project.entity.CustomQuestion;
import com.fissionlabs.demo_project.mapper.QuestionMapper;
import com.fissionlabs.demo_project.repository.ChoiceRepository;
import com.fissionlabs.demo_project.repository.CustomQuestionRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomQuestionService {

    private static final Logger logger = LoggerFactory.getLogger(CustomQuestionService.class);

    private CustomQuestionRepository customQuestionRepository;

    private ChoiceRepository choiceRepository;

    private QuestionMapper questionMapper;

    @Autowired
    public CustomQuestionService(CustomQuestionRepository customQuestionRepository, ChoiceRepository choiceRepository, QuestionMapper questionMapper) {
        this.customQuestionRepository = customQuestionRepository;
        this.choiceRepository = choiceRepository;
        this.questionMapper = questionMapper;
    }

    public List<QuestionDTO> getAllQuestions() {
        try {
            List<CustomQuestion> questions = customQuestionRepository.findAll();
            return mapQuestionsForFetch(questions);
        } catch (Exception ex) {
            logger.error("Error loading data from database", ex);
            return Collections.emptyList();
        }
    }

    private List<QuestionDTO> mapQuestionsForFetch(List<CustomQuestion> questions) {
        return questions.stream()
                .map(question -> {
                    QuestionDTO questionDTO = questionMapper.customQuestionToQuestionDTO(question);

                    // Fetch choices by questionId
                    List<Choice> choices = choiceRepository.findByQuestionId(question.getId());
                    questionDTO.setChoices(choices.stream().map(questionMapper::choiceToChoiceDTO).collect(Collectors.toList()));

                    return questionDTO;
                })
                .collect(Collectors.toList());
    }

    public boolean createQuestionnaires(List<QuestionDTO> questions) {
        try {
            mapQuestionsForInsert(questions);
            return true;
        } catch (Exception ex) {
            logger.error("Error while saving data into database", ex);
        }
        return false;
    }

    @Transactional
    private List<CustomQuestion> mapQuestionsForInsert(List<QuestionDTO> questionDTOList) {

        // Map all questions and save them
        List<CustomQuestion> customQuestions = questionDTOList.stream()
                .map(questionDTO -> {
                    // Map QuestionDTO to CustomQuestion
                    CustomQuestion customQuestion = questionMapper.questionDTOToCustomQuestion(questionDTO);

                    // Save the customQuestion (we need questionId for choice table)
                    CustomQuestion savedCustomQuestion = customQuestionRepository.save(customQuestion);

                    // Collect all the choices from all questions at once
                    List<Choice> allChoices = questionDTO.getChoices().stream()
                            .map(choiceDTO -> {
                                Choice choice = questionMapper.choiceDTOToChoice(choiceDTO);
                                choice.setQuestionId(savedCustomQuestion.getId());
                                return choice;
                            })
                            .collect(Collectors.toList());

                    // Save all choices in one batch
                    choiceRepository.saveAll(allChoices);

                    return customQuestion;
                })
                .collect(Collectors.toList());

        return customQuestions;
    }


}
