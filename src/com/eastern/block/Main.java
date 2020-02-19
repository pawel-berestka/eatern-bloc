package com.eastern.block;

import com.eastern.block.data.InputData;
import com.eastern.block.data.OutputData;
import com.eastern.block.file.InputDataReader;
import com.eastern.block.file.OutputDataWriter;

import java.io.FileNotFoundException;

public class Main {
    private static final String DEFAULT_INPUT_FILE_PATH = "C:\\dev\\src\\pizza-algorithm\\src\\com\\eastern\\block\\pizzas";
    private static final String DEFAULT_OUTPUT_FILE_PATH = "C:\\dev\\src\\pizza-algorithm\\src\\com\\eastern\\block\\result.out";

    public static void main(String[] args) throws FileNotFoundException {
        String inputFilePath;
        String outputFilePath;

        if(args.length == 2){
            inputFilePath=args[0];
            outputFilePath=args[1];
        } else {
            inputFilePath = DEFAULT_INPUT_FILE_PATH;
            outputFilePath = DEFAULT_OUTPUT_FILE_PATH;
        }

        InputData inputData = new InputDataReader().readData(inputFilePath);
        System.out.println(inputData);

        OutputData outputData = new OutputData();
        outputData.setTypesOfPizzas(10);
        outputData.setPizzasIndexes(new Integer[]{1, 2, 3, 4, 5});
        new OutputDataWriter().writeData(outputFilePath, outputData);
    }
}
