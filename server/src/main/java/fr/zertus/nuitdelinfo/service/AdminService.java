package fr.zertus.nuitdelinfo.service;

import fr.zertus.nuitdelinfo.entity.Question;
import fr.zertus.nuitdelinfo.entity.User;
import fr.zertus.nuitdelinfo.exception.DataNotFoundException;
import fr.zertus.nuitdelinfo.model.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    private boolean isAdmin() throws DataNotFoundException {
        if (userService.getCurrentUser() == null) {
            return false;
        }
        return userService.getCurrentUser().isAdmin();
    }

    public List<User> getAllUsers() throws DataNotFoundException {
        if (!isAdmin()) {
            throw new DataNotFoundException("You are not an admin");
        }
        return userService.getAllUsers();
    }

    public User getUserById(Long id) throws DataNotFoundException {
        if (!isAdmin()) {
            throw new DataNotFoundException("You are not an admin");
        }
        return userService.getUser(id);
    }

    public boolean deleteUserById(Long id) throws DataNotFoundException {
        if (!isAdmin()) {
            throw new DataNotFoundException("You are not an admin");
        }
        return userService.deleteUser(id);
    }

    public boolean updateUserById(Long id, RegisterDTO user) throws DataNotFoundException {
        if (!isAdmin()) {
            throw new DataNotFoundException("You are not an admin");
        }
        return userService.updateUser(id, user);
    }

    public boolean addQuestion(Question question) throws DataNotFoundException {
        if (!isAdmin()) {
            throw new DataNotFoundException("You are not an admin");
        }
        questionService.add(question);
        return true;
    }


}
