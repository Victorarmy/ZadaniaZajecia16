package com.example.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryRepository {

    private List<String> categories;

    public CategoryRepository() {
        this.categories = new ArrayList<>();
        categories.add("Psy");
        categories.add("Koty");
        categories.add("Inne");
    }

    public List<String> getAll() {
        return categories;
    }

    public void addNewCategory(String name) {
        categories.add(name);
    }
}
