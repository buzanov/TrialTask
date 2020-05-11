package ru.itis.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import ru.itis.task.repository.UserRepository;
import ru.itis.task.scope.ThreadScope;

@SpringBootTest
class TaskApplicationTests {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ApplicationContext context;


    @Test
    public void customScopeTest() {
        System.out.println("ApplicationContext initialized");

        System.out.println("Retrieve 'Register'");
        Register register1 = context.getBean(Register.class);
        System.out.println("Retrieve 'Register' again");
        Register register2 = context.getBean(Register.class);
        System.out.println("Register1 == Register2: " + (register1 == register2));

        System.out.println("Clear thread scope");
        ThreadScope threadScope = context.getBean(ThreadScope.class);

        threadScope.clear();

        System.out.println("Register1 == Register2: " + (register1 == register2));

        System.out.println("Retrieve 'Register'");
        Register register3 = context.getBean(Register.class);
        System.out.println("Retrieve 'Register' again");
        Register register4 = context.getBean(Register.class);
        System.out.println("Register3 == Register4: " + (register3 == register4));
    }

}
