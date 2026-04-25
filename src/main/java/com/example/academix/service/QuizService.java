package com.example.academix.service;

import com.example.academix.dto.request.QuizRequest;
import com.example.academix.model.Quiz;
import com.example.academix.model.Questions;
import com.example.academix.repository.QuizRepository;
import com.example.academix.repository.QuestionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuestionsRepository questionsRepository;

    public QuizService(QuizRepository quizRepository, QuestionsRepository questionsRepository) {
        this.quizRepository = quizRepository;
        this.questionsRepository = questionsRepository;
    }

    public Quiz createQuiz(QuizRequest request) {
        Quiz quiz = new Quiz();
        quiz.setTitle(request.getTitle());
        // Frontend doesn't send quizByClasses, so we set a default or leave null depending on DB constraints
        // For now let's leave it, if it's required we will need to set it to a default.
        quiz.setTimeLimitSeconds(request.getTimeLimitSeconds());
        
        java.util.List<Questions> qEntities = new java.util.ArrayList<>();
        if (request.getQuestions() != null) {
            for (QuizRequest.QuestionDto qDto : request.getQuestions()) {
                Questions q = new Questions();
                q.setQuestion(qDto.getText());
                q.setQuestionType(com.example.academix.myEnums.QuestionType.OTHER); // default

                if (qDto.getOptions() != null) {
                    if (qDto.getOptions().size() > 0) q.setOptionA(qDto.getOptions().get(0).getText());
                    if (qDto.getOptions().size() > 1) q.setOptionB(qDto.getOptions().get(1).getText());
                    if (qDto.getOptions().size() > 2) q.setOptionC(qDto.getOptions().get(2).getText());
                    if (qDto.getOptions().size() > 3) q.setOptionD(qDto.getOptions().get(3).getText());
                    if (qDto.getOptions().size() > 4) q.setOptionE(qDto.getOptions().get(4).getText());

                    for (int i = 0; i < qDto.getOptions().size(); i++) {
                        if (qDto.getOptions().get(i).getId().equals(qDto.getCorrectOptionId())) {
                            switch(i) {
                                case 0: q.setCorrectAnswer(com.example.academix.myEnums.Answers.A); break;
                                case 1: q.setCorrectAnswer(com.example.academix.myEnums.Answers.B); break;
                                case 2: q.setCorrectAnswer(com.example.academix.myEnums.Answers.C); break;
                                case 3: q.setCorrectAnswer(com.example.academix.myEnums.Answers.D); break;
                                case 4: q.setCorrectAnswer(com.example.academix.myEnums.Answers.E); break;
                            }
                        }
                    }
                }
                qEntities.add(q);
            }
        }
        quiz.setQuestions(qEntities);
        return quizRepository.save(quiz);
    }

    public Quiz getQuiz(Long id) {
        return quizRepository.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public com.example.academix.dto.response.QuizResultResponse submitQuiz(Long id, java.util.Map<String, String> userAnswers) {
        Quiz quiz = getQuiz(id);
        
        int total = 0;
        int correct = 0;
        int wrong = 0;
        int unanswered = 0;
        
        if (quiz.getQuestions() != null) {
            total = quiz.getQuestions().size();
            for (Questions q : quiz.getQuestions()) {
                String uAnswerStr = userAnswers != null ? userAnswers.get(String.valueOf(q.getId())) : null;
                if (uAnswerStr == null || uAnswerStr.trim().isEmpty()) {
                    unanswered++;
                } else {
                    if (q.getCorrectAnswer() != null && q.getCorrectAnswer().name().equalsIgnoreCase(uAnswerStr.trim())) {
                        correct++;
                    } else {
                        wrong++;
                    }
                }
            }
        }
        
        double percentage = total > 0 ? ((double) correct / total) * 100.0 : 0.0;
        
        return com.example.academix.dto.response.QuizResultResponse.builder()
                .totalQuestions(total)
                .correctAnswers(correct)
                .wrongAnswers(wrong)
                .unanswered(unanswered)
                .percentage(percentage)
                .build();
    }
}
