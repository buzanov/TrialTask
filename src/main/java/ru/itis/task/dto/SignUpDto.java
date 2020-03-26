package ru.itis.task.dto;

import lombok.Data;

@Data
public class SignUpDto {
    String login;
    String password;
    String role;
}
