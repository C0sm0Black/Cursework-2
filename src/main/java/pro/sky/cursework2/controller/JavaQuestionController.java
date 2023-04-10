package pro.sky.cursework2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pro.sky.cursework2.domain.Question;
import pro.sky.cursework2.exception.WrongQuestionException;
import pro.sky.cursework2.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/java")
public class JavaQuestionController {

    private final QuestionService questionService;

    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongQuestionException.class)
    public String handlerException(RuntimeException e) {
        return String.format("Ошибка №%s.<br>%s", HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @GetMapping
    public Collection<Question> getQuestions() {
        return questionService.getAll();
    }

    @GetMapping("/add")
    public Question addQuestion(@RequestParam String question, @RequestParam String answer) {
        return questionService.add(question, answer);
    }

    @GetMapping("/remove")
    public Question removeQuestion(@RequestParam String question, @RequestParam String answer) {
        return questionService.remove(question, answer);
    }

}

