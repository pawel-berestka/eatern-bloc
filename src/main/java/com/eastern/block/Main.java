package com.eastern.block;

import com.eastern.block.algorithm.Algorithm;
import com.eastern.block.data.InputData;
import com.eastern.block.data.OutputData;
import com.eastern.block.file.InputDataReader;
import com.eastern.block.file.OutputDataWriter;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;

public class Main {
    //        private static final String DEFAULT_INPUT_FILE_PATH = "C:\\dev\\src\\asd\\pizza-algorithm\\src\\main\\resources\\a_example.in";
//    private static final String DEFAULT_INPUT_FILE_PATH = "C:\\dev\\src\\asd\\pizza-algorithm\\src\\main\\resources\\b_small.in";
//    private static final String DEFAULT_INPUT_FILE_PATH = "C:\\dev\\src\\asd\\pizza-algorithm\\src\\main\\resources\\c_medium.in";
//    private static final String DEFAULT_INPUT_FILE_PATH = "C:\\dev\\src\\asd\\pizza-algorithm\\src\\main\\resources\\d_quite_big.in";
//    private static final String DEFAULT_INPUT_FILE_PATH = "C:\\dev\\src\\asd\\pizza-algorithm\\src\\main\\resources\\e_also_big.in";
    private static final String DEFAULT_INPUT_FILE_PATH = "C:\\dev\\hashcode\\eatern-bloc\\src\\main\\resources\\example.in";
    private static final String DEFAULT_OUTPUT_FILE_PATH = "C:\\dev\\hashcode\\eatern-bloc\\src\\main\\resources\\result.out";

    private static final InputDataReader inputDataReader = new InputDataReader();
    private static final OutputDataWriter outputDataWriter = new OutputDataWriter();


    public static void main(String[] args) throws FileNotFoundException {
        Instant startTime = Instant.now();

        String inputFilePath;
        String outputFilePath;

        if(args.length == 2){
            inputFilePath=args[0];
            outputFilePath=args[1];
        } else {
            inputFilePath = DEFAULT_INPUT_FILE_PATH;
            outputFilePath = DEFAULT_OUTPUT_FILE_PATH;
        }

        InputData inputData = inputDataReader.readData(inputFilePath);
        System.out.println(inputData);

        OutputData outputData = new Algorithm().calculate(inputData);
        outputDataWriter.writeData(outputFilePath, outputData);

        Instant endTime = Instant.now();
        System.out.println("Execution time: " + getElapsedTimeInMilis(startTime, endTime) + "ms.");
    }

    private static long getElapsedTimeInMilis(Instant startTime, Instant endTime) {
        return Duration.between(startTime, endTime).toMillis();
    }
}
