package fr.zertus.nuitdelinfo.service;

import fr.zertus.nuitdelinfo.entity.User;
import fr.zertus.nuitdelinfo.model.ApiResponse;
import fr.zertus.nuitdelinfo.model.RegisterDTO;
import fr.zertus.nuitdelinfo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegisterUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    public ApiResponse<User> register(RegisterDTO register) {
        if (register.getEmail().isEmpty() || register.getUsername().isEmpty() || register.getPassword().isEmpty())
            throw new IllegalArgumentException("Missing fields!");
        if (!isEmailValid(register.getEmail()))
            throw new IllegalArgumentException("Invalid email address!");

        User user = new User();
        user.setUsername(register.getUsername());
        user.setEmail(register.getEmail());
        user.setPassword(passwordEncoder.encode(register.getPassword()));

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already in use!");
        } else if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use!");
        } else {
            return ApiResponse.ok(userRepository.save(user));
        }
    }

    public static boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

}
