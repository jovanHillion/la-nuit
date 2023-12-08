package fr.zertus.nuitdelinfo.controller;

import fr.zertus.nuitdelinfo.entity.Question;
import fr.zertus.nuitdelinfo.entity.User;
import fr.zertus.nuitdelinfo.exception.DataNotFoundException;
import fr.zertus.nuitdelinfo.model.ApiResponse;
import fr.zertus.nuitdelinfo.model.RegisterDTO;
import fr.zertus.nuitdelinfo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * USER MANAGEMENT
     */

    @GetMapping("/user")
    public ApiResponse<List<User>> getAllUsers() throws DataNotFoundException {
        List<User> users = adminService.getAllUsers();
        return ApiResponse.ok(users);
    }

    @GetMapping("/user/{id}")
    public ApiResponse<User> getUserById(@PathVariable Long id) throws DataNotFoundException {
        User user = adminService.getUserById(id);
        return ApiResponse.ok(user);
    }

    @DeleteMapping("/user/{id}")
    public ApiResponse<String> deleteUserById(@PathVariable Long id) throws DataNotFoundException {
        boolean deleted = adminService.deleteUserById(id);
        if (deleted) {
            return ApiResponse.noContent();
        } else {
            return ApiResponse.notFound("User not found");
        }
    }

    @PatchMapping("/user/{id}")
    public ApiResponse<String> updateUserById(@PathVariable Long id, @RequestBody RegisterDTO registerDTO) throws DataNotFoundException {
        boolean updated = adminService.updateUserById(id, registerDTO);
        if (updated) {
            return ApiResponse.noContent();
        } else {
            return ApiResponse.notFound("User not found");
        }
    }

    /**
     * QUESTION MANAGEMENT
     */

    @PostMapping("/question")
    public ApiResponse<String> addQuestion(@RequestBody Question question) throws DataNotFoundException {
        boolean added = adminService.addQuestion(question);
        if (added) {
            return ApiResponse.noContent();
        } else {
            return ApiResponse.badRequest("Question already exists");
        }
    }

}
