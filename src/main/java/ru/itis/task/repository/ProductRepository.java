package ru.itis.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.itis.task.model.Product;

import java.util.List;


@Repository
@Component
public interface ProductRepository extends JpaRepository<Product, Long> {

    
}
