package pro.sky.cursework2.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pro.sky.cursework2.domain.Question;
import pro.sky.cursework2.exception.WrongAmountQuestionException;

import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = ExaminerServiceImpl.class)
@ExtendWith(SpringExtension.class)
class ExaminerServiceImplTest {

    @Autowired
    private ExaminerService examinerService;

    @MockBean
    private QuestionService questionService;

    @Test
    void getQuestions_success() {

        //Подготовка входных данных

        Question q1 = new Question("A", "A");
        Question q2 = new Question("B", "B");
        Question q3 = new Question("C", "C");
        Question q4 = new Question("D", "D");
        Question q5 = new Question("E", "E");
        Question q6 = new Question("F", "F");
        Question q7 = new Question("G", "G");

        Set<Question> questionsSet = Set.of(q1, q2, q3, q4, q5, q6, q7);

        int amount = 1;

        //Подготовка ожидаемого результата

        when(questionService.getAll()).thenReturn(questionsSet);
        when(questionService.getRandomQuestion()).thenReturn(q1);
        Set<Question> expectedQuestion = Set.of(q1);

        //Начало теста

        Collection<Question> actualQuestion = examinerService.getQuestions(amount);

        assertEquals(expectedQuestion, actualQuestion);
        verify(questionService).getAll();
        verify(questionService).getRandomQuestion();
        assertEquals(amount, actualQuestion.size());

    }

    @Test
    void getQuestions_WrongAmountQuestionExceptionFirst() {

        //Подготовка входных данных

        int amount = -1;

        //Подготовка ожидаемого результата

        String expectedMessage = "Введенное количство вопросов должно быть не меньше 1!";

        //Начало теста

        Exception exception = assertThrows(WrongAmountQuestionException.class,
                () -> examinerService.getQuestions(amount));

        assertEquals(expectedMessage, exception.getMessage());

    }

    @Test
    void getQuestions_WrongAmountQuestionExceptionSecond() {

        //Подготовка входных данных

        Question q1 = new Question("A", "A");
        Question q2 = new Question("B", "B");
        Question q3 = new Question("C", "C");
        Question q4 = new Question("D", "D");
        Question q5 = new Question("E", "E");
        Question q6 = new Question("F", "F");
        Question q7 = new Question("G", "G");

        Set<Question> questionsSet = Set.of(q1, q2, q3, q4, q5, q6, q7);

        int amount = 9;

        //Подготовка ожидаемого результата

        when(questionService.getAll()).thenReturn(questionsSet);

        String expectedMessage = "Количество вопросов в списке меньше, чем вы ввели!" +
                " Введите количесто не больше чем " + questionService.getAll().size();

        //Начало теста

        Exception exception = assertThrows(WrongAmountQuestionException.class,
                () -> examinerService.getQuestions(amount));

        assertEquals(expectedMessage, exception.getMessage());

    }

}