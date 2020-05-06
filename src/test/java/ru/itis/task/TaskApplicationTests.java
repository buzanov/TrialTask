package ru.itis.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.itis.task.repository.UserRepository;

@SpringBootTest
class TaskApplicationTests {

    @Autowired
    UserRepository userRepository;
    @Test
    void contextLoads() {
        System.out.println(userRepository.findUserByLogin("admin").get().getOrders());
        System.out.println(userRepository.findById(1L).get().getOrders());
    }

}
