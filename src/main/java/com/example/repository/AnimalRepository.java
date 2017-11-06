package com.example.repository;

import com.example.model.Animal;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Repository
public class AnimalRepository {

    private List<Animal> animalList;

    public AnimalRepository() {
        this.animalList = new ArrayList<>();
        animalList.add(new Animal(1L, "Reksio", "cos", "Psy"));
        animalList.add(new Animal(2L, "Azor", "cos", "Koty", "www.exaple.com"));
        animalList.add(new Animal(3L, "Husajn (Syryjskie imie: Mohamed Doglidov)", "Pies terrorysta, zamachowiec. Po ucieczce z Syrii wjeżdża w ludzi ciężarówkami, i podkłada bomby w autobusach. Jego nasławniejsza akcja to wjazd w ludzi, w Paryżu ciężarówką lodziarza. Ogólnie miły, nie gryzie.", "Inne", "http://i231.photobucket.com/albums/ee124/casazaza/osamadog2.jpg"));
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
                             .equals(animal.getName()) && animalInList.getDescription()
                                                                           .equals(animal.getDescription()) && animalInList.getCategory()
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

    public void changeAnimal(Animal animal) {
        ListIterator<Animal> iterator = animalList.listIterator();
        boolean isDone = false;
        while (iterator.hasNext() && !isDone) {
            Animal animalInList = iterator.next();
            if (animalInList.getId()
                            .equals(animal.getId())) {
                iterator.remove();
                iterator.add(animal);
                isDone = true;
            }
        }
    }
}
