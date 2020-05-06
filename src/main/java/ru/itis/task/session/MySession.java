package ru.itis.task.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import ru.itis.task.model.Cart;
import ru.itis.task.model.User;

@Component
@SessionScope
public class MySession {
    Cart cart;
    User user;

    public MySession() {
        this.cart = new Cart();
    }

    public Cart getCart() {
        return cart;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void cleanCart() {
        cart = new Cart();
    }

}