package ru.itis.task;

import org.springframework.stereotype.Component;
import ru.itis.task.annotation.ThreadScope;

@Component
@ThreadScope
public class Register {

    public Register() {
        System.out.println("- - - Register initialized");
    }
}