package pro.sky.cursework2.service;

import pro.sky.cursework2.domain.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getQuestions(int amount);

}
