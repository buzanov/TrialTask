package ru.itis.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.task.model.User;
import ru.itis.task.repository.UserRepository;

import java.util.UUID;

@Component
public class SignUpServiceImpl implements SignUpService {
    @Autowired
    UserRepository userRepository;

    @Override
    public void signUp(User user) {
        userRepository.save(user);
    }

    @Override
    public void signUpWithGoogle(String email) {
        userRepository.save(User.builder()
                .login(UUID.randomUUID().toString())
                .hashPassword("")
                .email(email).build()
        );
    }
}
