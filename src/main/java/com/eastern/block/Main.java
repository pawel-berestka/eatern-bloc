package com.eastern.block;

import com.eastern.block.algorithm.Algorithm;
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
    //        private static final String DEFAULT_INPUT_FILE_PATH = "C:\\dev\\src\\asd\\pizza-algorithm\\src\\main\\resources\\a_example.in";
//    private static final String DEFAULT_INPUT_FILE_PATH = "C:\\dev\\src\\asd\\pizza-algorithm\\src\\main\\resources\\b_small.in";
//    private static final String DEFAULT_INPUT_FILE_PATH = "C:\\dev\\src\\asd\\pizza-algorithm\\src\\main\\resources\\c_medium.in";
//    private static final String DEFAULT_INPUT_FILE_PATH = "C:\\dev\\src\\asd\\pizza-algorithm\\src\\main\\resources\\d_quite_big.in";
//    private static final String DEFAULT_INPUT_FILE_PATH = "C:\\dev\\src\\asd\\pizza-algorithm\\src\\main\\resources\\e_also_big.in";
    private static final String DEFAULT_INPUT_FILE_PATH = "C:\\dev\\hashcode\\eatern-bloc\\src\\main\\resources\\a_example.txt";
    private static final String DEFAULT_INPUT_FILE_PATH_A = "C:\\dev\\hashcode\\eatern-bloc\\src\\main\\resources\\a_example.txt";
    private static final String DEFAULT_INPUT_FILE_PATH_B = "C:\\dev\\hashcode\\eatern-bloc\\src\\main\\resources\\b_read_on.txt";
    private static final String DEFAULT_INPUT_FILE_PATH_C = "C:\\dev\\hashcode\\eatern-bloc\\src\\main\\resources\\c_incunabula.txt";
    private static final String DEFAULT_INPUT_FILE_PATH_D = "C:\\dev\\hashcode\\eatern-bloc\\src\\main\\resources\\d_tough_choices.txt";
    private static final String DEFAULT_INPUT_FILE_PATH_E = "C:\\dev\\hashcode\\eatern-bloc\\src\\main\\resources\\e_so_many_books.txt";
    private static final String DEFAULT_INPUT_FILE_PATH_F = "C:\\dev\\hashcode\\eatern-bloc\\src\\main\\resources\\f_libraries_of_the_world.txt";
    private static final String DEFAULT_OUTPUT_FILE_PATH = "C:\\dev\\hashcode\\eatern-bloc\\src\\main\\resources\\a_example.out";

    private static final List<String> allInputs
            = Arrays.asList(DEFAULT_INPUT_FILE_PATH_A, DEFAULT_INPUT_FILE_PATH_B, DEFAULT_INPUT_FILE_PATH_C, DEFAULT_INPUT_FILE_PATH_D, DEFAULT_INPUT_FILE_PATH_E, DEFAULT_INPUT_FILE_PATH_F);

    private static final InputDataReader inputDataReader = new InputDataReader();
    private static final OutputDataWriter outputDataWriter = new OutputDataWriter();

    private static final Integer MODE = 0;

    public static void main(String[] args) throws FileNotFoundException {
        if (MODE == 1) {
            processFile(DEFAULT_INPUT_FILE_PATH, DEFAULT_OUTPUT_FILE_PATH);
        } else {
            int sum = 0;
            for (String path : allInputs) {
                sum += processFile(path, DEFAULT_OUTPUT_FILE_PATH);
            }
            System.out.println("TOTAL SCORE FROM ALL FILES IS " + sum);
        }

    }

    private static Integer processFile(String inputFilePath, String outputFilePath) throws FileNotFoundException {
        Instant startTime = Instant.now();

        InputData inputData = inputDataReader.readData(inputFilePath);
        System.out.println(inputData);

        OutputData outputData = new Algorithm().calculate(inputData);
        outputDataWriter.writeData(outputFilePath, outputData);

        Integer totalScore = getTotalScore(outputData, inputData);
        System.out.println("FINALOWY WYNIK TO: " + totalScore);

        Instant endTime = Instant.now();
        System.out.println("Execution time: " + getElapsedTimeInMilis(startTime, endTime) + "ms.");
        return totalScore;
    }

    private static long getElapsedTimeInMilis(Instant startTime, Instant endTime) {
        return Duration.between(startTime, endTime).toMillis();
    }

    public static Integer getTotalScore(OutputData outputData, InputData inputData) {
        int totalSignupTime = 0;
        int score = 0;
        Set<Integer> processedBooksIndexes = new HashSet<>();
        for (OutputLibrary outputLibrary : outputData.getOutputLibraries()) {
            totalSignupTime += findLibrary(inputData.getLibraries(), outputLibrary.getLibraryIndex()).getSignupProcessTime();
            for (Integer bookIndex : outputLibrary.getBookIndexesToSend()) {
                if (processedBooksIndexes.contains(bookIndex)) {
                    System.out.println("POTWORKA INDEKSU " + bookIndex);
                } else {
                    processedBooksIndexes.add(bookIndex);
                    score += inputData.getBooksScores().get(bookIndex);
                }
            }
        }

        if (totalSignupTime > inputData.getDaysForScannig()) {
            System.out.println("HEHEHE PRZEKROCZYLISMY CZAS. POWINNO BYC " + inputData.getDaysForScannig() + " A JEST " + totalSignupTime);
        }
        return score;
    }

    private static Library findLibrary(List<Library> libraryList, Integer libraryIndex) {
        List<Library> myLibrary = libraryList.stream()
                .filter(library -> library.getLibraryIndex().equals(libraryIndex))
                .collect(toList());

        if (myLibrary.size() != 1) {
            System.out.println("COS SIE SPIEPRZYLO, INDEKSOW " + libraryIndex + " JEST " + myLibrary.size());
        }

        return myLibrary.get(0);
    }
}
