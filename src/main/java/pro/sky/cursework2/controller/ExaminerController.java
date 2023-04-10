package pro.sky.cursework2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pro.sky.cursework2.domain.Question;
import pro.sky.cursework2.exception.WrongAmountQuestionException;
import pro.sky.cursework2.service.ExaminerService;

import java.util.Collection;

@RestController
@RequestMapping("/get/")
public class ExaminerController {

    private final ExaminerService examinerService;

    public ExaminerController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongAmountQuestionException.class)
    public String handlerException(RuntimeException e) {
        return String.format("Ошибка №%s.<br>%s", HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }


    @GetMapping("/{amount}")
    public Collection<Question> getQuestions(@PathVariable("amount") int amount) {
        return examinerService.getQuestions(amount);
    }

}

