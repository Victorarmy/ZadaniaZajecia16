package com.example.controller;

import com.example.model.Animal;
import com.example.model.SortTypes;
import com.example.repository.AnimalRepository;
import com.example.repository.CategoryRepository;
import com.example.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private AnimalRepository animalRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public HomeController(AnimalRepository animalRepository, CategoryRepository categoryRepository) {
        this.animalRepository = animalRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public String home(@RequestParam(required = false) String sort, Model model) {
        List<Animal> animals = animalRepository.getAll();
        if (sort != null) {
            Comparator<Animal> comparator = SortTypes.getComparator(sort);
            Collections.sort(animals, comparator);
        }
        model.addAttribute("animals", animals);
        model.addAttribute("categories", categoryRepository.getAll());
        model.addAttribute("chosenCategory", "Zwierzaki");
        return "homePage";
    }
}