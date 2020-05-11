package ru.itis.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.itis.task.model.Order;

@Component
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
