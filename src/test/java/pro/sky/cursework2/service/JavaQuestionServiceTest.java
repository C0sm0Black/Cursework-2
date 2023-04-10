package pro.sky.cursework2.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pro.sky.cursework2.domain.Question;
import pro.sky.cursework2.exception.WrongQuestionException;

import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = JavaQuestionService.class)
@ExtendWith(SpringExtension.class)
class JavaQuestionServiceTest {

    @Autowired
    private QuestionService questionService;

    @MockBean
    private Random random;

    @Test
    void getRandomQuestion_success() {

        //Подготовка входных данных

        Question q1 = new Question("A", "A");
        Question q2 = new Question("B", "B");
        Question q3 = new Question("C", "C");
        Question q4 = new Question("D", "D");
        Question q5 = new Question("E", "E");
        Question q6 = new Question("F", "F");
        Question q7 = new Question("G", "G");

        //Подготовка ожидаемого результата

        when(random.nextInt(7)).thenReturn(1)
                .thenReturn(2)
                .thenReturn(3);

        Set<Question> expectedQuestion = Set.of(q2, q3, q4);

        //Начало теста

        Set<Question> actualQuestion = Set.of(questionService.getRandomQuestion()
                ,questionService.getRandomQuestion()
                ,questionService.getRandomQuestion());

        assertEquals(expectedQuestion, actualQuestion);

    }

    @Test
    void add_success() {

        //Подготовка входных данных

        String question = "ABC";
        String answer = "CBA";


        //Подготовка ожидаемого результата

        Question expectedQuestion = new Question(question, answer);

        //Начало теста

        int sizeBeforeAdd = questionService.getAll().size();
        Question actualQuestion = questionService.add(question, answer);

        assertEquals(expectedQuestion, actualQuestion);
        assertEquals(sizeBeforeAdd + 1, questionService.getAll().size());

    }

    @Test
    void add_withWrongQuestionException() {

        //Подготовка входных данных

        String question = "A";
        String answer = "A";

        //Подготовка ожидаемого результата

        String expectedMessage = "Такой вопрос уже есть!";

        //Начало теста

        Exception exception = assertThrows(WrongQuestionException.class,
                () -> questionService.add(question, answer));

        assertEquals(expectedMessage, exception.getMessage());

    }

    @Test
    void remove_success() {

        //Подготовка входных данных

        String question = "B";
        String answer = "B";


        //Подготовка ожидаемого результата

        Question expectedQuestion = new Question(question, answer);

        //Начало теста

        int sizeBeforeRemove = questionService.getAll().size();
        Question actualQuestion = questionService.remove(question, answer);

        assertEquals(expectedQuestion, actualQuestion);
        assertEquals(sizeBeforeRemove - 1, questionService.getAll().size());

        questionService.add(expectedQuestion);

    }

    @Test
    void remove_withWrongQuestionException() {

        //Подготовка входных данных

        String question = "DDD";
        String answer = "DDD";

        //Подготовка ожидаемого результата

        String expectedMessage = "Такого вопроса и ответа нет в колекции!";

        //Начало теста

        Exception exception = assertThrows(WrongQuestionException.class,
                () -> questionService.remove(question, answer));

        assertEquals(expectedMessage, exception.getMessage());

    }

    @Test
    void getAll_success() {

        //Подготовка входных данных

        Question q1 = new Question("A", "A");
        Question q2 = new Question("B", "B");
        Question q3 = new Question("C", "C");
        Question q4 = new Question("D", "D");
        Question q5 = new Question("E", "E");
        Question q6 = new Question("F", "F");
        Question q7 = new Question("G", "G");

        //Подготовка ожидаемого результата

        Set<Question> expectedSet = Set.of(q1, q2, q3, q4, q5, q6, q7);

        //Начало теста

        Set<Question> actualSet = Set.of(q1, q2, q3, q4, q5, q6, q7);

        assertEquals(expectedSet, actualSet);

    }

}