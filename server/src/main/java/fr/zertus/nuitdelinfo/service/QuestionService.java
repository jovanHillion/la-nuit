package fr.zertus.nuitdelinfo.service;

import fr.zertus.nuitdelinfo.entity.Question;
import fr.zertus.nuitdelinfo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public void add(Question question) {
        questionRepository.save(question);
    }

    public List<Question> getAllQuestions() {
        return (List<Question>) questionRepository.findAll();
    }

    public Question getQuestionById(long id) {
        return questionRepository.findById(id).orElse(null);
    }

    public Question getRandQuestion() {
        return questionRepository.findRandQuestion();
    }

}
