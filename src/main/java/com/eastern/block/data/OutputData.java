package com.eastern.block.data;

public class OutputData {
    private Integer typesOfPizzas = 0;
    private Integer[] pizzasIndexes = new Integer[0];

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
