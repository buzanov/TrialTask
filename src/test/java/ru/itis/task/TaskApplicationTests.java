package ru.itis.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import ru.itis.task.repository.UserRepository;
import ru.itis.task.scope.ThreadScope;

import javax.servlet.ServletContext;

@SpringBootTest
class TaskApplicationTests {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ServletContext context;

    @Test
    public void test() {
        System.out.println("Context path -" + context.getRealPath("/resources"));
        System.out.println("Context path -" + context.getRealPath("/resources/static/img") );
        System.out.println("Context path -" + context.getRealPath("/resources/static/img") );
        System.out.println("Context path -" + context.getRealPath("/resources/static/img") );
        System.out.println("Context path -" + context.getRealPath("/resources/static/img") );
        System.out.println("Context path -" + context.getRealPath("/resources/static/img") );
        System.out.println("Context path -" + context.getRealPath("/resources/static/img") );
    }

}
