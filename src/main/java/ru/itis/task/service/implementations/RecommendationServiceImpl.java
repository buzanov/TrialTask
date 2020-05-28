package ru.itis.task.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import ru.itis.task.model.Category;
import ru.itis.task.model.Order;
import ru.itis.task.model.Product;
import ru.itis.task.repository.OrderRepository;
import ru.itis.task.repository.ProductRepository;
import ru.itis.task.service.interfaces.RecommendationService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
@Component
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getRecommendedItemsByUserId(Long id) {
        List<Product> allProducts = productRepository.findAll();
        List<Order> orders = orderRepository.findAllByUserId(id);
        List<Product> products = new ArrayList<>();
        orders.forEach(order -> products.addAll(order.getOrdered_products()));
        Map<Category, Integer> map = new HashMap<>();
        Map<Category, List<Product>> resultMap = new HashMap<>();
        Map<Category, Integer> priceMap = new HashMap<>();
        allProducts.forEach(product -> {
            if (!resultMap.containsKey(product.getCategory())) {
                resultMap.put(product.getCategory(), new ArrayList<>());
            }
            resultMap.get(product.getCategory()).add(product);
        });
        products.forEach(product -> {
            if (!priceMap.containsKey(product.getCategory())) {
                priceMap.put(product.getCategory(), product.getPrice());
            } else {
                priceMap.put(product.getCategory(), priceMap.get(product.getCategory()) + product.getPrice());
            }
            if (!map.containsKey(product.getCategory())) {
                map.put(product.getCategory(), 1);
            } else {
                map.put(product.getCategory(), map.get(product.getCategory()) + 1);
            }

        });
        priceMap.entrySet()
                .forEach(e -> e.setValue(e.getValue() / map.get(e.getKey())));
        List<Product> result = new LinkedList<>();
        map.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue)).forEachOrdered(entry -> {
            Collections
                    .sort(resultMap.get(entry.getKey()),
                            (o1, o2) -> Math.abs(o2.getPrice() - priceMap.get(entry.getKey())) - Math.abs(o1.getPrice() - priceMap.get(entry.getKey())));
            result.addAll(resultMap.get(entry.getKey()));
        });
        return result;
    }
}
