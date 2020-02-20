package com.eastern.block.algorithm;

import com.eastern.block.data.InputData;
import com.eastern.block.data.OutputData;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {
    private int bestScore = 0;
    private List<Integer> bestPizzasSet = new ArrayList<>();

    public OutputData calculate(InputData inputData) {
        Integer[] pizzas = inputData.getPizzas();
        Integer target = inputData.getMaxNumberOfSlices();

        Integer actualScore = 0;
        List<Integer> usedPizzas = new ArrayList<>(pizzas.length);
        for (int i = pizzas.length - 1; i >= 1; --i) {
            actualScore += pizzas[i];
            usedPizzas.add(i);

            for (int k = i - 1; k >= 0; --k) {
                if ((target - actualScore) - pizzas[k] > 0) {
                    actualScore += pizzas[k];
                    usedPizzas.add(k);
                }
            }

            if (actualScore > bestScore) {
//                System.out.println(String.format("Replacing oldScore: %s with newScore: %s\nOld: %s\nNew: %s", bestScore, actualScore, bestPizzasSet, usedPizzas));
                bestScore = actualScore;
                bestPizzasSet = new ArrayList<>(usedPizzas);
            }
            actualScore = 0;
            usedPizzas = new ArrayList<>(pizzas.length);
        }

        Integer[] result = new Integer[bestPizzasSet.size()];
//        System.out.println(String.format("Best result: %s", bestScore));
        return new OutputData(bestPizzasSet.size(), bestPizzasSet.toArray(result));
    }
}
