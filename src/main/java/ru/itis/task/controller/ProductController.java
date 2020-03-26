package ru.itis.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.task.model.Product;
import ru.itis.task.repository.ProductRepository;
import ru.itis.task.session.MySession;

import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    MySession session;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ModelAndView products() {
        ModelAndView modelAndView = new ModelAndView("products_page");
        modelAndView.addObject("products", productRepository.findAll());
        return modelAndView;
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.GET)
    public String addProduct(@RequestParam Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        optionalProduct.ifPresent(product -> session.getCart().addProduct(product));
        return "redirect:/products";
    }

    @RequestMapping(value = "/createProduct", method = RequestMethod.GET)
    public String createProduct() {
        return "new_product";
    }

    @RequestMapping(value = "/createProduct", method = RequestMethod.POST)
    public String addProductToBase(@RequestParam String name, @RequestParam String price) {
        productRepository.save(Product.builder()
                .name(name)
                .price(Integer.parseInt(price))
                .build());
        return "new_product";
    }
}
