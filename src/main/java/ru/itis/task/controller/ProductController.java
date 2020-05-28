package ru.itis.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.task.dto.ProductDto;
import ru.itis.task.model.Category;
import ru.itis.task.model.Order;
import ru.itis.task.model.Product;
import ru.itis.task.repository.OrderRepository;
import ru.itis.task.repository.ProductRepository;
import ru.itis.task.service.interfaces.RecommendationService;
import ru.itis.task.session.MySession;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    MySession session;

    @Autowired
    ServletContext context;

    @Autowired
    RecommendationService recommendationService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public ModelAndView products() {
        ModelAndView modelAndView = new ModelAndView("products_page");
        modelAndView.addObject("products", productRepository.findAll());
        return modelAndView;
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> addProduct(@RequestParam Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            if (session.getCart().getProductList().contains(product)) {
                session.getCart().getProductList().remove(product);
                return ResponseEntity.ok().build();
            }
            session.getCart().addProduct(product);
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(value = "/createProduct", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String createProduct() {
        return "new_product";
    }

    @RequestMapping(value = "/admin/createProduct", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity addProductToBase(@RequestBody ProductDto dto) {
        productRepository.save(Product.builder()
                .name(dto.getName())
                .category(Category.valueOf(dto.getCategory()))
                .price(Integer.parseInt(dto.getPrice()))
                .build());
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/recommend")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView recommended() {
        ModelAndView modelAndView = new ModelAndView("products_page");
        modelAndView.addObject("products",
                recommendationService
                        .getRecommendedItemsByUserId(session.getUser().getId()));
        return modelAndView;
    }

}
