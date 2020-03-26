package ru.itis.task.service;

import ru.itis.task.model.Order;
import ru.itis.task.model.Product;
import ru.itis.task.model.User;
import ru.itis.task.session.MySession;

import java.util.List;

public interface OrderService {
    Order makeOrder(List<Product> productList, User user);
}
