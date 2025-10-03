package com.akash.quiz_app.service;

import com.akash.quiz_app.dto.requestDTO.CreateQuizRequest;
import com.akash.quiz_app.dto.responseDTO.QuizResponse;
import com.akash.quiz_app.entity.Quiz;
import com.akash.quiz_app.exceptions.ResourceNotFoundException;
import com.akash.quiz_app.mappers.QuizMapper;
import com.akash.quiz_app.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {
    private final QuizMapper quizMapper;
    private final QuizRepository quizRepository;
    public QuizServiceImpl(QuizMapper quizMapper, QuizRepository quizRepository) {
        this.quizMapper = quizMapper;
        this.quizRepository=quizRepository;
    }

    @Override
    public QuizResponse createQuiz(CreateQuizRequest request) {
        Quiz quiz = quizMapper.toEntity(request);
        quiz=quizRepository.save(quiz);
        return quizMapper.toResponse(quiz);
    }

    @Override
    public List<QuizResponse> getAllQuizzes() {
        List<Quiz> quizzes = quizRepository.findAll();
        return quizMapper.toResponseList(quizzes);
    }

    @Override
    public QuizResponse getQuizById(Long id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Quiz not found with "+id));
        return quizMapper.toResponse(quiz);
    }
}
