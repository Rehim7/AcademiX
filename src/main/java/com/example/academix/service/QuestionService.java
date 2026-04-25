package com.example.academix.service;

import com.example.academix.dto.request.QuestionsRequest;
import com.example.academix.dto.response.QuestionsResponse;
import com.example.academix.exceptions.QuestionAlreadyExist;
import com.example.academix.model.Questions;
import com.example.academix.repository.QuestionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionsRepository  questionsRepository;

    public QuestionService(QuestionsRepository questionsRepository) {
        this.questionsRepository = questionsRepository;
    }

    public List<Questions> findAll() {
        return questionsRepository.findAll();
    }
    public QuestionsResponse createQuestion(QuestionsRequest questionsRequest) {
        List<Questions> byNumber = questionsRepository.findByNumber(questionsRequest.getNumber());
        if (!byNumber.isEmpty()) {
            throw new QuestionAlreadyExist("Question already exist");
        }
        Questions questions = new Questions();
        questions.setNumber(questionsRequest.getNumber());
        questions.setQuestion(questionsRequest.getQuestion());
        questions.setCorrectAnswer(questionsRequest.getCorrectAnswer());
        questions.setCorrect(true);
        questions.setQuestionType(questionsRequest.getQuestionType());
        questions.setAnswer(questionsRequest.getAnswer());
        questions.setOptionA(questionsRequest.getOptionA());
        questions.setOptionB(questionsRequest.getOptionB());
        questions.setOptionC(questionsRequest.getOptionC());
        questions.setOptionD(questionsRequest.getOptionD());
        questions.setOptionE(questionsRequest.getOptionE());
        Questions save = questionsRepository.save(questions);
        QuestionsResponse questionsResponse = new QuestionsResponse();
        questionsResponse.setAnswer(save.getAnswer());
        questionsResponse.setQuestionType(save.getQuestionType());
        questionsResponse.setQuestion(save.getQuestion());
        questionsResponse.setNumber(save.getNumber());
        questionsResponse.setOptionA(save.getOptionA());
        questionsResponse.setOptionB(save.getOptionB());
        questionsResponse.setOptionC(save.getOptionC());
        questionsResponse.setOptionD(save.getOptionD());
        questionsResponse.setOptionE(save.getOptionE());
        return questionsResponse;
    }

    public void deleteQuestion(Long id) {
        questionsRepository.deleteById(id);
    }



}
