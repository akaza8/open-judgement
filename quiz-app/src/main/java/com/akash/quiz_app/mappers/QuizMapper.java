package com.akash.quiz_app.mappers;

import com.akash.quiz_app.dto.requestDTO.CreateQuizRequest;
import com.akash.quiz_app.dto.responseDTO.QuizResponse;
import com.akash.quiz_app.entity.Quiz;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuizMapper {
    Quiz toEntity(CreateQuizRequest request);
    @Mapping(target = "questionCount", expression = "java(quiz.getQuestions().size())")
    QuizResponse toResponse(Quiz quiz);
    List<QuizResponse> toResponseList(List<Quiz> quizzes);
}
