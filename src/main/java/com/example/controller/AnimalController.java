package com.example.controller;

import com.example.model.Animal;
import com.example.repository.AnimalRepository;
import com.example.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/animal")
public class AnimalController {

    private AnimalRepository animalRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public AnimalController(AnimalRepository animalRepository, CategoryRepository categoryRepository) {
        this.animalRepository = animalRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/{id}")
    public String getAnimal(@PathVariable Long id, Model model) {
        Animal animal = animalRepository.getAnimalById(id);
        model.addAttribute("animal", animal);
        return "animalPage";
    }

    @GetMapping("/addAnimal")
    public String addAnimal(Model model) {
        model.addAttribute("animal", new Animal());
        model.addAttribute("categoryList", categoryRepository.getAll());
        return "addAnimalForm";
    }

    @PostMapping("/addAnimal")
    public String addNewAnimal(@ModelAttribute Animal animal) {
        boolean contains = animalRepository.checkIfContains(animal);
        if (!contains) {
            animalRepository.addNewAnimalAndSetId(animal);
        }
        System.out.println(animal);
        return "redirect:/animal/" + animal.getId();
    }
}
