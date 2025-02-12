package com.fissionlabs.demo_project.mapper;

import com.fissionlabs.demo_project.dto.ResponseDTO;
import com.fissionlabs.demo_project.entity.CustomQuestionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ResponseMapper {

    // Mapping from CustomQuestion to QuestionDTO
    @Mapping(source = "questionId", target = "questionId")
    @Mapping(source = "responseText", target = "responseText")
    @Mapping(source = "optionId", target = "responseId")
    CustomQuestionResponse responseDTOToCustomQuestionResponse(ResponseDTO responseDTO);
}
