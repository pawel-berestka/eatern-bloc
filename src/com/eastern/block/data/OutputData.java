package com.eastern.block.data;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OutputData {
    private Integer typesOfPizzas;
    private Integer[] pizzasIndexes;

    public Integer getTypesOfPizzas() {
        return typesOfPizzas;
    }

    public void setTypesOfPizzas(Integer typesOfPizzas) {
        this.typesOfPizzas = typesOfPizzas;
    }

    public Integer[] getPizzasIndexes() {
        return pizzasIndexes;
    }

    public void setPizzasIndexes(Integer[] pizzasIndexes) {
        this.pizzasIndexes = pizzasIndexes;
    }
}
