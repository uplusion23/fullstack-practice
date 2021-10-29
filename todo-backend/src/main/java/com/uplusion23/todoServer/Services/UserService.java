package com.uplusion23.todoServer.Services;

import com.uplusion23.todoServer.Exceptions.UserExistsException;
import com.uplusion23.todoServer.Models.User;
import com.uplusion23.todoServer.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Object registerUser(User userAccount) {
        if (this.userExists(userAccount.getUsername())) {
            return Map.of("error", "User already exists");
        }
        final User user = new User();
        user.setUsername(userAccount.getUsername());
        user.setPassword(this.bCryptPasswordEncoder.encode(userAccount.getPassword()));
        user.setCreatedAt(LocalDate.now());
        user.setUpdatedAt(LocalDate.now());
        user.setRole(2);

        return this.userRepository.save(user);
    }

    public void saveRegisteredUser(User user) {
        this.userRepository.save(user);
    }

    public void deleteUser(User user) {
        this.userRepository.delete(user);
    }

    public Optional<User> getUserById(Long id) {
        return this.userRepository.findById(id);
    }

    public Object authenticateUser(String username, String password) {
        if (!this.userExists(username)) {
            throw new UserExistsException("User does not exist");
        }
        User user = this.userRepository.findByUsername(username);
        if (user != null) {
            if (this.bCryptPasswordEncoder.matches(password, user.getPassword())) {
                return user;
            } else {
                return Map.of("error", "Username and/or password incorrect");
            }
        } else return Map.of("error", "User does not exist");
    }

    private boolean userExists(final String username) {
        return this.userRepository.existsByUsername(username);
    }
}
