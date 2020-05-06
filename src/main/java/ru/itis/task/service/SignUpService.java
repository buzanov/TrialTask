package ru.itis.task.service;

import ru.itis.task.model.User;

public interface SignUpService {
    void signUp(User user);
    void signUpWithGoogle(String email);
}
