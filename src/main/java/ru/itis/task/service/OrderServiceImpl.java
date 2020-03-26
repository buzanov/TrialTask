package ru.itis.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.task.model.Order;
import ru.itis.task.model.Product;
import ru.itis.task.model.User;
import ru.itis.task.session.MySession;

import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {
    @Autowired
    MySession session;

    @Override

    public Order makeOrder(List<Product> productList, User user) {
        Order order = Order.builder()
                .ordered_products(productList)
                .user(user).build();
        session.cleanCart();
        return order;
    }
}
