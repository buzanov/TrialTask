package ru.itis.task.aspects;

import lombok.SneakyThrows;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.task.model.User;
import ru.itis.task.utils.Logger;

@Aspect
@Component
public class LoggingAspect {

    @Autowired
    Logger logger;

    @After(value = "execution(* ru.itis.task.service.OrderService.makeOrder(..))")
    public void after(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        User user = (User) args[1];
        System.out.println("!!!");
        logger.log("Пользователь - " + user + " сделал заказ");
    }

    @SneakyThrows
    @Around(value = "execution(* ru.itis.task.service.*.*(*))")
    public void time(ProceedingJoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();
        joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        logger.log("time of execution " + joinPoint.getSignature().getName() + "  " + (startTime - endTime));
    }
}
