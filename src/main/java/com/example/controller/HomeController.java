package com.example.controller;

import com.example.model.Animal;
import com.example.repository.AnimalRepository;
import com.example.repository.CategoryRepository;
import com.example.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private AnimalRepository animalRepository;
    private CategoryRepository categoryRepository;
    private FilterService filterService;

    @Autowired
    public HomeController(AnimalRepository animalRepository, CategoryRepository categoryRepository, FilterService filterService) {
        this.animalRepository = animalRepository;
        this.categoryRepository = categoryRepository;
        this.filterService = filterService;
    }

    @GetMapping
    public String home(@RequestParam(required = false) String category, Model model) {
        if (category != null && !category.equals("Wszystkie")) {
            List<Animal> filteredAnimals = filterService.filterAnimalsByCategory(category);
            model.addAttribute("animals", filteredAnimals);
        } else {
            model.addAttribute("animals", animalRepository.getAll());
        }
        model.addAttribute("categories", categoryRepository.getAll());
        return "homePage";
    }

}
