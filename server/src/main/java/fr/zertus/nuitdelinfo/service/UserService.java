package fr.zertus.nuitdelinfo.service;

import fr.zertus.nuitdelinfo.entity.User;
import fr.zertus.nuitdelinfo.exception.DataNotFoundException;
import fr.zertus.nuitdelinfo.model.RegisterDTO;
import fr.zertus.nuitdelinfo.repository.UserRepository;
import fr.zertus.nuitdelinfo.security.SecurityUtils;
import fr.zertus.nuitdelinfo.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean userExists(String id) {
        long userId = Long.parseLong(id);
        return userExists(userId);
    }

    public boolean userExists(long id) {
        User user = userRepository.findById(id).orElse(null);
        return user != null;
    }

    public User getUser(String id) {
        long userId = Long.parseLong(id);
        return getUser(userId);
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User getUser(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getCurrentUser() throws DataNotFoundException {
        return getUser(SecurityUtils.getCurrentUserId());
    }

    public boolean updateUser(RegisterDTO user) throws DataNotFoundException {
        User currentUser = getCurrentUser();
        if (currentUser == null)
            return false;
        if (!user.getEmail().isEmpty() && RegisterUserService.isEmailValid(user.getEmail()))
            currentUser.setEmail(user.getEmail());
        if (!user.getPassword().isEmpty())
            currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(currentUser);
        return true;
    }

    public boolean updateUser(long id, RegisterDTO user) throws DataNotFoundException {
        User currentUser = getUser(id);
        if (currentUser == null)
            return false;
        if (!user.getEmail().isEmpty() && RegisterUserService.isEmailValid(user.getEmail()))
            currentUser.setEmail(user.getEmail());
        if (!user.getPassword().isEmpty())
            currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(currentUser);
        return true;
    }

    public String saveUserAvatar(User user, MultipartFile file) throws IOException {
        FileUploadUtil.saveFile("users-avatar", user.getId() + "", file);
        user.setAvatar("https://ndi.zertus.fr/user/avatar/" + user.getId());
        saveUser(user);
        return user.getAvatar();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public boolean deleteUser(long id) throws DataNotFoundException {
        User user = getUser(id);
        if (user == null) {
            throw new DataNotFoundException("User not found");
        }
        userRepository.delete(user);
        return true;
    }

}
