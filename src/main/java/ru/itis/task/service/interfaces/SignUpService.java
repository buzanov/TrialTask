package ru.itis.task.service.interfaces;

import ru.itis.task.model.User;

public interface SignUpService {
    void signUp(User user);
    void signUpWithGoogle(String email);
}
