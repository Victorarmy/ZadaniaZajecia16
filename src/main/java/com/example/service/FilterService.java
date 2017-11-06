package com.example.service;

import com.example.model.Animal;
import com.example.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilterService {

    private AnimalRepository animalRepository;

    @Autowired
    public FilterService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> filterAnimalsByCategory(String category) {
        return animalRepository.getAll()
                               .stream()
                               .filter(e -> e.getCategory()
                                             .equals(category))
                               .collect(Collectors.toList());
    }
}
