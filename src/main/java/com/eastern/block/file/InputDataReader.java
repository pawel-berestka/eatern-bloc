package com.eastern.block.file;

import com.eastern.block.data.InputData;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InputDataReader {
    public InputData readData(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner fileScanner = new Scanner(file);
        String firstLine = fileScanner.nextLine().replace("\n", "");
        String secondLine = fileScanner.nextLine().replace("\n", "");

        InputData inputData = new InputData();
        String[] firstLineParsed = firstLine.split(" ");
        String[] secondLineParsed = secondLine.split(" ");
        inputData.setMaxNumberOfSlices(Integer.valueOf(firstLineParsed[0]));
        inputData.setDifferentTypes(Integer.valueOf(firstLineParsed[1]));

        Integer[] pizzas = new Integer[inputData.getDifferentTypes()];
        for (int i = 0; i < inputData.getDifferentTypes(); ++i) {
            pizzas[i] = Integer.valueOf(secondLineParsed[i]);
        }

        inputData.setPizzas(pizzas);
        return inputData;
    }
}
