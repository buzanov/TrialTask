package ru.itis.task.service.interfaces;

import ru.itis.task.model.Product;

import java.util.List;

public interface RecommendationService {
    List<Product> getRecommendedItemsByUserId(Long id);
}
