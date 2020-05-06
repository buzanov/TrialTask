package ru.itis.task.service;

import ru.itis.task.dto.SignUpDto;
import ru.itis.task.model.User;

import java.util.Optional;

public interface SignInService {
    void signIn(SignUpDto form);
    Optional<User> signIn(String email);
}
