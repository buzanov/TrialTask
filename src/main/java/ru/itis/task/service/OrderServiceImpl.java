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
    public Order makeOrder(List<Product> products, User user) {
        return Order.builder()
                .ordered_products(products)
                .user(user)
                .build();
    }

    @Override
    public Order makeOrder() {
        Order order = Order.builder()
                .ordered_products(session.getCart().getProductList())
                .user(session.getUser()).build();
        session.cleanCart();
        return order;
    }
}
