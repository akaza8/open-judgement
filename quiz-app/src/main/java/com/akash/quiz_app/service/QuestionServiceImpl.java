package com.akash.quiz_app.service;

import com.akash.quiz_app.Validator.QuestionValidator;
import com.akash.quiz_app.dto.requestDTO.AddQuestionRequest;
import com.akash.quiz_app.exceptions.ValidateException;
import org.springframework.transaction.annotation.Transactional;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{
    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;
    private final QuestionValidator questionValidator;
    private final QuestionMapper questionMapper;
    @Override
    @Transactional(readOnly = true)
    public ScoreResponse submitAnswer(Long quizID, SubmitAnswerRequest request) {
        if(!quizRepository.existsById((quizID))) throw new ResourceNotFoundException("Quiz not found with "+quizID);
        List<Question> questions = questionRepository.findAllByQuizIdWithOptions(quizID);
        Map<Long, Question> questionMap = questions.stream().collect(Collectors.toMap(Question::getId, q->q));
        int correctAnswers=0;
        for(AnswerDto answer : request.answers()) {
            Question question = questionMap.get(answer.questionId());
            if(question==null) throw new ValidateException("Invalid question ID: "+answer.questionId());
            if(isAnswerCorrect(question, answer)) correctAnswers++;
        }
        int totalQuestions = questions.size();
        double percentage = totalQuestions>0?(double)correctAnswers/totalQuestions*100:0;
        return ScoreResponse.builder()
                .score(correctAnswers)
                .total(totalQuestions)
                .percentage(percentage)
                .build();
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

    @Override
    @Transactional(readOnly = true)
    public List<QuestionResponse> getQuizQuestions(long id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Quiz not found with "+id));
        return questionMapper.toResponseList(questionRepository.findAllByQuizIdWithOptions(id));
    }

    private boolean isAnswerCorrect(Question question, AnswerDto answer){
        if(question.getType() == QuestionType.TEXT) {
            return question.getOptions().stream().filter(Option::isCorrect)
                    .anyMatch(option -> option.getText().equalsIgnoreCase(answer.textAnswer()));
        } else {
            List<Long> correctOptions = question.getOptions().stream().filter(Option::isCorrect).map(Option::getId).toList();
            List<Long> selectedIds = answer.selectedOptionIds();
            if(selectedIds == null || selectedIds.isEmpty()){
                return false;
            }
            return selectedIds.size()==correctOptions.size() && new HashSet<>(correctOptions).containsAll(selectedIds);
        }
    }
}
