package ru.itis.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.task.model.Order;
import ru.itis.task.repository.OrderRepository;
import ru.itis.task.service.OrderService;
import ru.itis.task.session.MySession;

@Controller
public class OrderController {

    @Autowired
    MySession session;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ModelAndView page() {
        ModelAndView modelAndView = new ModelAndView("orders_page");
        modelAndView.addObject("orders", orderRepository.findAll());
        return modelAndView;
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    public ModelAndView order(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("order_page");
        orderRepository.findById(id).ifPresent(o -> modelAndView.addObject("order", o));
        return modelAndView;
    }

    @RequestMapping(value = "/makeOrder", method = RequestMethod.GET)
    public String makeOrder() {
        Order order = orderService.makeOrder(session.getCart().getProductList(), session.getUser());
        orderRepository.save(order);
        return "redirect:/products";
    }
}
