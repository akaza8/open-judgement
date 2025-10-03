package com.akash.quiz_app.mappers;

import com.akash.quiz_app.dto.requestDTO.AddQuestionRequest;
import com.akash.quiz_app.dto.requestDTO.OptionDTO;
import com.akash.quiz_app.dto.responseDTO.QuestionResponse;
import com.akash.quiz_app.entity.Option;
import com.akash.quiz_app.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "quiz", ignore = true)
    @Mapping(target = "options", ignore = true)
    Question toEntity(AddQuestionRequest questionDTO);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "question", ignore = true)
    Option toOption(OptionDTO optionDTO);
    QuestionResponse toResponse(Question question);
    List<QuestionResponse> toResponseList(List<Question> questions);
}
