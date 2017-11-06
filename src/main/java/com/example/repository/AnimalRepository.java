package com.example.repository;

import com.example.model.Animal;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AnimalRepository {

    private List<Animal> animalList;

    public AnimalRepository() {
        this.animalList = new ArrayList<>();
        animalList.add(new Animal(1L, "Reksio", "cos", "Psy"));
        animalList.add(new Animal(2L, "Azor", "cos", "Koty"));
    }

    public List<Animal> getAll() {
        return animalList;
    }

    public Animal getAnimalById(Long id) {
        for (Animal animal : animalList) {
            if (animal.getId()
                      .equals(id)) {
                return animal;
            }
        }
        return null;
    }

    public boolean checkIfContains(Animal animal) {
        for (Animal animalInList : animalList) {
            if (animalInList.getName()
                             .equals(animal.getCategory()) || animalInList.getDescription()
                                                                           .equals(animal.getDescription()) || animalInList.getCategory()
                                                                                                                            .equals(animal.getCategory())) {
                return true;
            }
        }
        return false;
    }

    public void addNewAnimalAndSetId(Animal animal) {
        animal.setId((long) animalList.size() + 1);
        animalList.add(animal);
    }
}
