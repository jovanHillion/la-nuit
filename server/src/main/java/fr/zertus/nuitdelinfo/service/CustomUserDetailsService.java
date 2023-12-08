package fr.zertus.nuitdelinfo.service;

import fr.zertus.nuitdelinfo.entity.User;
import fr.zertus.nuitdelinfo.repository.UserRepository;
import fr.zertus.nuitdelinfo.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
            return new CustomUserDetails(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
