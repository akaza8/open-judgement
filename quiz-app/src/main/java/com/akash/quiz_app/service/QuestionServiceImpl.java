package com.akash.quiz_app.service;

import com.akash.quiz_app.Validator.QuestionValidator;
import com.akash.quiz_app.dto.requestDTO.AddQuestionRequest;
import com.akash.quiz_app.dto.requestDTO.AnswerDto;
import com.akash.quiz_app.dto.requestDTO.OptionDTO;
import com.akash.quiz_app.dto.requestDTO.SubmitAnswerRequest;
import com.akash.quiz_app.dto.responseDTO.QuestionResponse;
import com.akash.quiz_app.dto.responseDTO.ScoreResponse;
import com.akash.quiz_app.entity.Option;
import com.akash.quiz_app.entity.Question;
import com.akash.quiz_app.entity.QuestionType;
import com.akash.quiz_app.entity.Quiz;
import com.akash.quiz_app.exceptions.ResourceNotFoundException;
import com.akash.quiz_app.mappers.QuestionMapper;
import com.akash.quiz_app.repository.QuestionRepository;
import com.akash.quiz_app.repository.QuizRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{
    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;
    private final QuestionValidator questionValidator;
    private final QuestionMapper questionMapper;
    @Override
    public ScoreResponse submitAnswer(Long quizID, SubmitAnswerRequest request) {
        if(!quizRepository.existsById((quizID))) throw new ResourceNotFoundException("Quiz not found with "+quizID);
        return null;
    }

    @Override
    public QuestionResponse addQuestion(Long quizId, AddQuestionRequest request) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new ResourceNotFoundException("Quiz not found with "+quizId));
        questionValidator.validate(request);
        Question question = questionMapper.toEntity(request);
        question.setQuiz(quiz);
        for(OptionDTO optiondto:request.options()) {
            Option option = questionMapper.toOption(optiondto);
            option.setQuestion(question);
            question.getOptions().add(option);
        }
        question=questionRepository.save(question);
        return questionMapper.toResponse(question);
     }

    private boolean isAnswerCorrect(Question question, AnswerDto answer){
        if(question.getType() == QuestionType.TEXT) {
            return question.getOptions().stream().filter(Option::isCorrect)
                    .anyMatch(option->option.getText().equalsIgnoreCase(answer.textAnswer()));
        }
        return false;
    }
}
