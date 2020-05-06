package ru.itis.task.utils;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.FileWriter;

@Component
public class Logger {
    @Autowired
    @Qualifier("logFileWriter")
    FileWriter fileWriter;

    @SneakyThrows
    public void log(String s) {
        fileWriter.append(s + "\n");
        fileWriter.flush();

    }


}
