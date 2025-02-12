package com.fissionlabs.demo_project.mapper;

import com.fissionlabs.demo_project.dto.ChoiceDTO;
import com.fissionlabs.demo_project.dto.QuestionDTO;
import com.fissionlabs.demo_project.entity.Choice;
import com.fissionlabs.demo_project.entity.CustomQuestion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    // Mapping from CustomQuestion to QuestionDTO
    @Mapping(source = "id", target = "questionId")
    @Mapping(source = "question", target = "question")
    @Mapping(source = "isRequired", target = "required")
    @Mapping(source = "type", target = "type", defaultValue = "UNKNOWN")
    @Mapping(source = "choices", target = "choices", qualifiedByName = "mapChoices")
    QuestionDTO customQuestionToQuestionDTO(CustomQuestion question);

    // Mapping from Choice to ChoiceDTO
    @Mapping(source = "optionId", target = "optionId")
    @Mapping(source = "optionText", target = "optionText")
    ChoiceDTO choiceToChoiceDTO(Choice choice);

    // Mapping from Choice to ChoiceDTO
    @Mapping(source = "optionId", target = "optionId")
    @Mapping(source = "optionText", target = "optionText")
    Choice choiceDTOToChoice(ChoiceDTO choiceDTO);

    // Mapping from QuestionDTO to CustomQuestion
    @Mapping(source = "question", target = "question")
    @Mapping(source = "required", target = "isRequired")
    @Mapping(target = "type", expression = "java(mapType(questionDTO.getType()))") // Custom type mapping
    @Mapping(source = "choices", target = "choices", qualifiedByName = "mapChoicesToString")
    CustomQuestion questionDTOToCustomQuestion(QuestionDTO questionDTO);

    // Helper method for converting type string to Enum
    default CustomQuestion.QuestionType mapType(String type) {
        if (type == null) {
            return null;
        }
        return CustomQuestion.QuestionType.valueOf(type);
    }

    // Custom method to map String (comma-separated choices) to List<ChoiceDTO>
    @org.mapstruct.Named("mapChoices")
    default List<ChoiceDTO> mapChoices(String choices) {
        if (choices == null || choices.isEmpty()) {
            return List.of(); // Return an empty list if no choices
        }

        // Split the comma-separated choices and map each to a ChoiceDTO
        return Arrays.stream(choices.split(","))
                .map(choice -> {
                    ChoiceDTO choiceDTO = new ChoiceDTO();
                    choiceDTO.setOptionId(choice);
                    return choiceDTO;
                })
                .collect(Collectors.toList());
    }

    // Custom method to map List<ChoiceDTO> to a String (comma-separated)
    @org.mapstruct.Named("mapChoicesToString")
    default String mapChoicesToString(List<ChoiceDTO> choices) {
        if (choices == null || choices.isEmpty()) {
            return "";
        }

        return choices.stream()
                .map(choiceDTO -> choiceDTO.getOptionId().toString())
                .collect(Collectors.joining(","));
    }
}
