package com.example.controller;

import com.example.model.Animal;
import com.example.model.SortTypes;
import com.example.repository.AnimalRepository;
import com.example.repository.CategoryRepository;
import com.example.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/animal")
public class AnimalController {

    private AnimalRepository animalRepository;
    private CategoryRepository categoryRepository;
    private FilterService filterService;

    @Autowired
    public AnimalController(AnimalRepository animalRepository, CategoryRepository categoryRepository, FilterService filterService) {
        this.animalRepository = animalRepository;
        this.categoryRepository = categoryRepository;
        this.filterService = filterService;
    }

    @GetMapping("{category}/{id}")
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
        System.out.println(contains);
        if (!contains) {
            System.out.println("Wszedlem");
            animalRepository.addNewAnimalAndSetId(animal);
        }
        System.out.println(animal);
        return "redirect:/animal/" + animal.getCategory() + "/" + animal.getId();
    }

    @GetMapping("/{id}/edit")
    public String editAnimal(@PathVariable Long id, Model model) {
        model.addAttribute("animal", animalRepository.getAnimalById(id));
        model.addAttribute("categoryList", categoryRepository.getAll());
        return "editAnimalForm";
    }

    @PostMapping("/{id}/edit")
    public String editOldAnimal(@ModelAttribute Animal animal, @PathVariable Long id) {
        animal.setId(id);
        animalRepository.changeAnimal(animal);
        return "redirect:/animal/" + animal.getId();
    }

    @GetMapping("/{category}")
    public String animalsByCategory(@RequestParam(required = false) String sort, @PathVariable String category, Model model) {
        List<Animal> filteredAnimals = filterService.filterAnimalsByCategory(category);
        if (sort != null) {
            Comparator<Animal> comparator = SortTypes.getComparator(sort);
            Collections.sort(filteredAnimals, comparator);
        }
        model.addAttribute("animals", filteredAnimals);
        model.addAttribute("categories", categoryRepository.getAll());
        model.addAttribute("chosenCategory", category);
        return "homePage";
    }
}
