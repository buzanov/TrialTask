package ru.itis.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.task.dto.SignUpDto;
import ru.itis.task.model.User;
import ru.itis.task.repository.UserRepository;

import java.util.Optional;

@Component
public class SignInServiceImpl implements SignInService {
    @Autowired
    UserRepository userRepository;

    @Override
    public void signIn(SignUpDto form) {

    }

    @Override
    @Transactional
    public Optional<User> signIn(String email) {
        Optional<User> userOpt = userRepository.findUserByEmail(email);
        return userOpt;
    }
}
