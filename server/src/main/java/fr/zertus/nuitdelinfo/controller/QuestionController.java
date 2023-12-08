package fr.zertus.nuitdelinfo.controller;

import fr.zertus.nuitdelinfo.entity.Question;
import fr.zertus.nuitdelinfo.entity.User;
import fr.zertus.nuitdelinfo.exception.DataNotFoundException;
import fr.zertus.nuitdelinfo.model.ApiResponse;
import fr.zertus.nuitdelinfo.model.QuestionSubmitDTO;
import fr.zertus.nuitdelinfo.security.SecurityUtils;
import fr.zertus.nuitdelinfo.service.QuestionService;
import fr.zertus.nuitdelinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @GetMapping("/random")
    public ApiResponse<Question> getRandomQuestion() {
        Question question = questionService.getRandQuestion();
        return ApiResponse.ok(question);
    }

    @GetMapping
    public ApiResponse<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        return ApiResponse.ok(questions);
    }

    @GetMapping("/{id}")
    public ApiResponse<Question> getQuestionById(@PathVariable long id) {
        Question question = questionService.getQuestionById(id);
        return ApiResponse.ok(question);
    }

    @PostMapping("/submit")
    public ApiResponse<Boolean> submitQuestion(@RequestBody QuestionSubmitDTO questionDto) throws DataNotFoundException {
        Question question = questionService.getQuestionById(questionDto.getId());
        if (question == null) {
            throw new DataNotFoundException("Question not found");
        }

        boolean isCorrect = question.isResponse() == questionDto.isAnswer();
        if (isCorrect) {
            try {
                User user = userService.getCurrentUser();
                user.setPoints(user.getPoints() + 1);
                userService.saveUser(user);
            } catch (Exception e) {}
        } else {
            try {
                User user = userService.getCurrentUser();
                user.setPoints(user.getPoints() - 1);
                userService.saveUser(user);
            } catch (Exception e) {}
        }
        return ApiResponse.ok(isCorrect);
    }

}
