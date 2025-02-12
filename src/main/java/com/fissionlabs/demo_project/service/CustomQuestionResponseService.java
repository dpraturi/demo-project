package com.fissionlabs.demo_project.service;

import com.fissionlabs.demo_project.dto.ResponseDTO;
import com.fissionlabs.demo_project.entity.CustomQuestionResponse;
import com.fissionlabs.demo_project.mapper.ResponseMapper;
import com.fissionlabs.demo_project.repository.CustomQuestionResponseRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomQuestionResponseService {

    private static final Logger logger = LoggerFactory.getLogger(CustomQuestionResponseService.class);

    private CustomQuestionResponseRepository customQuestionResponseRepository;

    private ResponseMapper responseMapper;

    @Autowired
    public CustomQuestionResponseService(CustomQuestionResponseRepository customQuestionResponseRepository, ResponseMapper responseMapper) {
        this.customQuestionResponseRepository = customQuestionResponseRepository;
        this.responseMapper = responseMapper;
    }

    public boolean saveQuestionResponses(List<ResponseDTO> responses) {
        try {
            mapQuestionResponsesForInsert(responses);
            return true;
        } catch (Exception ex) {
            logger.error("Error while saving data into database", ex);
        }
        return false;
    }

    @Transactional
    private List<CustomQuestionResponse> mapQuestionResponsesForInsert(List<ResponseDTO> responses) {

        // Map all responses and save them
        return responses.stream().map(responseDTO -> {

            // Map responseDTO to CustomQuestionResponse
            CustomQuestionResponse customQuestionResponse = responseMapper.responseDTOToCustomQuestionResponse(responseDTO);

            // save responses to db
            customQuestionResponseRepository.save(customQuestionResponse);
            return customQuestionResponse;
        })
                .collect(Collectors.toList());
    }

}
