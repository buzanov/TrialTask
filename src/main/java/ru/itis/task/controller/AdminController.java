package ru.itis.task.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.task.model.Category;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminController {
    @GetMapping(value = "/admin/getCategories")
    public @ResponseBody List<Category> fetchCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(Category.Furniture);
        categories.add(Category.Accessories);
        categories.add(Category.Animals);
        categories.add(Category.Appliances);
        categories.add(Category.Devices);
        categories.add(Category.Kids);
        categories.add(Category.Car);
        categories.add(Category.Sport);
        return ResponseEntity.ok(categories).getBody();
    }
}
