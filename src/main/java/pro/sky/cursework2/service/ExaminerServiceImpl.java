package pro.sky.cursework2.service;

import org.springframework.stereotype.Service;
import pro.sky.cursework2.domain.Question;
import pro.sky.cursework2.exception.WrongAmountQuestionException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    public Collection<Question> getQuestions(int amount) {

        if (amount < 1) {
            throw new WrongAmountQuestionException("Введенное количство вопросов должно быть не меньше 1!");
        }

        if (questionService.getAll().size() < amount) {
            throw new WrongAmountQuestionException("Количество вопросов в списке меньше, чем вы ввели!" +
                    " Введите количесто не больше чем " + questionService.getAll().size());
        }

        Set<Question> questionSet = new HashSet<>();

        while (amount != questionSet.size()) {
            questionSet.add(questionService.getRandomQuestion());
        }

        return questionSet;

    }

}
