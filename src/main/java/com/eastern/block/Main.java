package com.eastern.block;

import com.eastern.block.algorithm.*;
import com.eastern.block.data.InputData;
import com.eastern.block.data.Library;
import com.eastern.block.data.OutputData;
import com.eastern.block.data.OutputLibrary;
import com.eastern.block.file.InputDataReader;
import com.eastern.block.file.OutputDataWriter;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class Main {
    private static final LibraryBooksGetter libraryBooksGetter = new LibraryBooksGetter();
    private static final NumberOfBooksGetter numberOfBooksGetter = new NumberOfBooksGetter();
    private static final LibraryScoreGetter libraryScoreGetter = new LibraryScoreGetter(libraryBooksGetter, numberOfBooksGetter);
    private static final BestLibraryGetter bestLibraryGetter = new BestLibraryGetter(libraryScoreGetter);
    private static final Algorithm algorithm = new Algorithm(bestLibraryGetter, libraryBooksGetter, numberOfBooksGetter);

    private static final String DEFAULT_INPUT_FILE_PATH = "C:\\dev\\hashcode\\eatern-bloc\\src\\main\\resources\\example.in";
    private static final String DEFAULT_OUTPUT_FILE_PATH = "C:\\dev\\hashcode\\eatern-bloc\\src\\main\\resources\\example.out";

    private static final String DEFAULT_INPUT_FILE_PATH_A = "C:\\dev\\hashcode\\eatern-bloc\\src\\main\\resources\\a_example.txt";
    private static final String DEFAULT_INPUT_FILE_PATH_B = "C:\\dev\\hashcode\\eatern-bloc\\src\\main\\resources\\b_read_on.txt";
    private static final String DEFAULT_INPUT_FILE_PATH_C = "C:\\dev\\hashcode\\eatern-bloc\\src\\main\\resources\\c_incunabula.txt";
    private static final String DEFAULT_INPUT_FILE_PATH_D = "C:\\dev\\hashcode\\eatern-bloc\\src\\main\\resources\\d_tough_choices.txt";
    private static final String DEFAULT_INPUT_FILE_PATH_E = "C:\\dev\\hashcode\\eatern-bloc\\src\\main\\resources\\e_so_many_books.txt";
    private static final String DEFAULT_INPUT_FILE_PATH_F = "C:\\dev\\hashcode\\eatern-bloc\\src\\main\\resources\\f_libraries_of_the_world.txt";
    private static final String DEFAULT_OUTPUT_FILE_PATH_A = "C:\\dev\\hashcode\\eatern-bloc\\src\\main\\resources\\a_example.out";
    private static final String DEFAULT_OUTPUT_FILE_PATH_B = "C:\\dev\\hashcode\\eatern-bloc\\src\\main\\resources\\b_read_on.out";
    private static final String DEFAULT_OUTPUT_FILE_PATH_C = "C:\\dev\\hashcode\\eatern-bloc\\src\\main\\resources\\c_incunabula.out";
    private static final String DEFAULT_OUTPUT_FILE_PATH_D = "C:\\dev\\hashcode\\eatern-bloc\\src\\main\\resources\\d_tough_choices.out";
    private static final String DEFAULT_OUTPUT_FILE_PATH_E = "C:\\dev\\hashcode\\eatern-bloc\\src\\main\\resources\\e_so_many_books.out";
    private static final String DEFAULT_OUTPUT_FILE_PATH_F = "C:\\dev\\hashcode\\eatern-bloc\\src\\main\\resources\\f_libraries_of_the_world.out";

    private static final List<String> allInputs
            = Arrays.asList(DEFAULT_INPUT_FILE_PATH_A,
            DEFAULT_INPUT_FILE_PATH_B,
            DEFAULT_INPUT_FILE_PATH_C,
            DEFAULT_INPUT_FILE_PATH_D,
            DEFAULT_INPUT_FILE_PATH_E,
            DEFAULT_INPUT_FILE_PATH_F);

    private static final List<String> allOutputs
            = Arrays.asList(DEFAULT_OUTPUT_FILE_PATH_A,
            DEFAULT_OUTPUT_FILE_PATH_B,
            DEFAULT_OUTPUT_FILE_PATH_C,
            DEFAULT_OUTPUT_FILE_PATH_D,
            DEFAULT_OUTPUT_FILE_PATH_E,
            DEFAULT_OUTPUT_FILE_PATH_F);


    private static final InputDataReader inputDataReader = new InputDataReader();
    private static final OutputDataWriter outputDataWriter = new OutputDataWriter();
    private static MODE mode = MODE.ALL_FILES;

    public static void main(String[] args) throws FileNotFoundException {
        if (mode == MODE.SINGLE_FILE) {
            processFile(DEFAULT_INPUT_FILE_PATH_D, DEFAULT_OUTPUT_FILE_PATH);
        } else if (mode == MODE.ALL_FILES) {
            int sum = 0;
            for (int i = 0; i < allInputs.size(); ++i) {
                sum += processFile(allInputs.get(i), allOutputs.get(i));
            }
            System.out.println("TOTAL SCORE FROM ALL FILES IS " + sum);
        }
    }

    private static Integer processFile(String inputFilePath, String outputFilePath) throws FileNotFoundException {
        Instant startTime = Instant.now();

        InputData inputData = inputDataReader.readData(inputFilePath);
//        System.out.println(inputData);

        OutputData outputData = algorithm.calculate(inputData);
        outputDataWriter.writeData(outputFilePath, outputData);

//        System.out.println(outputData);
        Integer totalScore = getTotalScore(outputData, inputData);
        System.out.println("Score from file is " + totalScore);

        Instant endTime = Instant.now();
        System.out.println("Execution time: " + getElapsedTimeInMilis(startTime, endTime) + "ms.");
        return totalScore;
    }

    public static Integer getTotalScore(OutputData outputData, InputData inputData) {
        int totalSignupTime = 0;
        int score = 0;
        Set<Integer> processedBooksIndexes = new HashSet<>();
        for (OutputLibrary outputLibrary : outputData.getOutputLibraries()) {
            totalSignupTime += findLibrary(inputData.getLibraries(), outputLibrary.getLibraryIndex()).getSignupProcessTime();
            for (Integer bookIndex : outputLibrary.getBookIndexesToSend()) {
                if (processedBooksIndexes.contains(bookIndex)) {
                    System.out.println("Doubled index " + bookIndex);
                } else {
                    processedBooksIndexes.add(bookIndex);
                    score += inputData.getBooksScores().get(bookIndex);
                }
            }
        }

        if (totalSignupTime > inputData.getDaysForScannig()) {
            System.out.println("Should be not more than, " + inputData.getDaysForScannig() + " but is " + totalSignupTime);
        }
        return score;
    }

    private static long getElapsedTimeInMilis(Instant startTime, Instant endTime) {
        return Duration.between(startTime, endTime).toMillis();
    }

    private static Library findLibrary(List<Library> libraryList, Integer libraryIndex) {
        List<Library> myLibrary = libraryList.stream()
                .filter(library -> library.getLibraryIndex().equals(libraryIndex))
                .collect(toList());

        if (myLibrary.size() != 1) {
            System.out.println("Something went wrong with library indexes...");
        }

        return myLibrary.get(0);
    }

    private enum MODE {
        SINGLE_FILE,
        ALL_FILES
    }
}
