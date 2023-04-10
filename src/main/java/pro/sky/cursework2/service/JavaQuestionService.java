package pro.sky.cursework2.service;

import org.springframework.stereotype.Service;
import pro.sky.cursework2.domain.Question;
import pro.sky.cursework2.exception.WrongQuestionException;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private static final Set<Question> questions = new HashSet<>();

    private final Random random;

    public JavaQuestionService(Random random) {
        this.random = random;
    }

    static {

        Question q1 = new Question("A", "A");
        Question q2 = new Question("B", "B");
        Question q3 = new Question("C", "C");
        Question q4 = new Question("D", "D");
        Question q5 = new Question("E", "E");
        Question q6 = new Question("F", "F");
        Question q7 = new Question("G", "G");

        questions.add(q1);
        questions.add(q2);
        questions.add(q3);
        questions.add(q4);
        questions.add(q5);
        questions.add(q6);
        questions.add(q7);

    }

    public Question add(String question, String answer) {

        Question q = new Question(question, answer);

        if (isRepeatedQuestion(q)) {
            throw new WrongQuestionException("Такой вопрос уже есть!");
        } else {
            return add(q);
        }

    }

    public Question add(Question question) {

        questions.add(question);
        return question;

    }

    public Question remove(String question, String answer) {

        Question removeQuestion = new Question(question, answer);

        if (!questions.contains(removeQuestion)) {
            throw new WrongQuestionException("Такого вопроса и ответа нет в колекции!");
        } else {

            questions.remove(removeQuestion);
            return removeQuestion;

        }

    }

    public Set<Question> getAll() {
        return questions;
    }

    public Question getRandomQuestion() {

        if (questions.size() == 0) {
            return null;
        }

        List<Question> questionList = new ArrayList<>(questions);

        return questionList.get(random.nextInt(questions.size()));

    }

    private boolean isRepeatedQuestion(Question question) {

        Question repeatedQuestion = questions.stream()
                .filter(e -> e.getQuestion().equalsIgnoreCase(question.getQuestion()))
                .findFirst().orElse(null);

        return repeatedQuestion != null;

    }

}
