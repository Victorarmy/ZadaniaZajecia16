package com.example.model;

import java.util.Comparator;

public enum SortTypes {
    ALPH("alph", Comparator.comparing(Animal::getName));

    private String parameterName;
    private Comparator<Animal> comparator;

    SortTypes(String parameterName, Comparator<Animal> comparator) {
        this.parameterName = parameterName;
        this.comparator = comparator;
    }


    public static Comparator<Animal> getComparator(String parameterName) {
        for (SortTypes sortType : SortTypes.values()) {
            if (sortType.parameterName.equals(parameterName)) {
                return sortType.comparator;
            }
        }
        return null;
    }
}
