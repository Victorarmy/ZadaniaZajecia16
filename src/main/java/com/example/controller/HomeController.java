package com.example.controller;

import com.example.model.Animal;
import com.example.model.SortTypes;
import com.example.repository.AnimalRepository;
import com.example.repository.CategoryRepository;
import com.example.service.FilterService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/category/addCategory")
    public String addCategory() {
        return "addCategoryForm";
    }

    @PostMapping("/category/addCategory")
    public String addNewCategory(@RequestParam String name, RedirectAttributes redirectAttributes) {
        if (categoryRepository.getAll().contains(name)){
            redirectAttributes.addFlashAttribute("message", "Taka kategoria juz istnieje");
            return "redirect:/category/addCategory";
        }
        categoryRepository.addNewCategory(name);
        return "redirect:/";
    }
}