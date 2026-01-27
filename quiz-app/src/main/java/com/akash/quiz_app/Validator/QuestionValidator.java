package com.akash.quiz_app.Validator;

import com.akash.quiz_app.dto.requestDTO.AddQuestionRequest;
import com.akash.quiz_app.dto.requestDTO.OptionDTO;
import com.akash.quiz_app.entity.QuestionType;
import com.akash.quiz_app.exceptions.ValidateException;
import org.springframework.stereotype.Component;

@Component
public class QuestionValidator {
    public void validate(AddQuestionRequest request) {
        validateCorrectAnswer(request);
        validateTextBasedAnswer(request);
    }

    private void validateCorrectAnswer(AddQuestionRequest request) {
        long correctCount = request.options().stream().filter(OptionDTO::isCorrect).count();
        if(request.type()== QuestionType.SINGLE_CHOICE) {
            if(correctCount != 1) throw new ValidateException("Single choice question should have only one correct answer");
        } else if (request.type()==QuestionType.MULTIPLE_CHOICE) {
            if(correctCount < 1) throw new ValidateException("Multiple choice question should have at least two correct answers");
        } else if (request.type()==QuestionType.TEXT) {
            if(correctCount<1) throw new ValidateException("Text question should have only one correct answer");
        }
    }

    private void validateTextBasedAnswer(AddQuestionRequest request) {
        if(request.type()==QuestionType.TEXT) {
            for(OptionDTO option: request.options()) {
                if(option.text().length()>300) {
                    throw new ValidateException("Text answer should be less than 300 characters");
                }
            }
        }
    }

}
