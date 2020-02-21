package com.eastern.block.algorithm;

import com.eastern.block.data.*;

import java.util.*;
import java.util.stream.Collectors;

public class Algorithm {
    private BestLibraryGetter bestLibraryGetter;
    private LibraryBooksGetter libraryBooksGetter;
    private NumberOfBooksGetter numberOfBooksGetter;

    public Algorithm(BestLibraryGetter bestLibraryGetter, LibraryBooksGetter libraryBooksGetter, NumberOfBooksGetter numberOfBooksGetter) {
        this.bestLibraryGetter = bestLibraryGetter;
        this.libraryBooksGetter = libraryBooksGetter;
        this.numberOfBooksGetter = numberOfBooksGetter;
    }

    public OutputData calculate(InputData inputData) {
        List<Library> libraries = new LinkedList<>(inputData.getLibraries());
        Integer wholeTime = inputData.getDaysForScannig();
        Integer timeLeft = wholeTime;
        Set<Book> chosenBooks = new HashSet<>();

        List<LibraryAndItsBooks> result = new ArrayList<>();
        while (!libraries.isEmpty()) {
            Optional<Library> bestLibraryOptional = bestLibraryGetter.getLibrary(libraries, chosenBooks, timeLeft);
            if (bestLibraryOptional.isPresent()) {
                Library bestLibrary = bestLibraryOptional.get();
                List<Book> booksFromBestLibrary = libraryBooksGetter.getBooksToTake(bestLibrary.getBooks(), chosenBooks, numberOfBooksGetter.getNumberOfBooksToTake(timeLeft, bestLibrary.getSignupProcessTime(), bestLibrary.getShipmentSpeed()));
                if (!booksFromBestLibrary.isEmpty()) {
                    LibraryAndItsBooks transformedBestLibrary = new LibraryAndItsBooks();
                    transformedBestLibrary.library = bestLibrary;
                    transformedBestLibrary.books = booksFromBestLibrary;
                    result.add(transformedBestLibrary);

                    chosenBooks.addAll(booksFromBestLibrary);
                    timeLeft -= bestLibrary.getSignupProcessTime();
                }

                libraries.remove(bestLibrary);
            }
        }

        return getOutputDataFromResult(result);
    }

    private OutputData getOutputDataFromResult(List<LibraryAndItsBooks> result) {
        OutputData outputData = new OutputData();
        outputData.setNumberOfLibraries(result.size());

        List<OutputLibrary> outputLibraries = new ArrayList<>();
        for (int i = 0; i < result.size(); ++i) {
            LibraryAndItsBooks libraryAndItsBooks = result.get(i);
            OutputLibrary outputLibrary = new OutputLibrary();
            outputLibrary.setBookIndexesToSend(libraryAndItsBooks.books.stream()
                    .map(Book::getIndex)
                    .collect(Collectors.toList()));
            outputLibrary.setNumberOfBooksToSend(libraryAndItsBooks.books.size());
            outputLibrary.setLibraryIndex(libraryAndItsBooks.library.getLibraryIndex());

            outputLibraries.add(outputLibrary);
        }
        outputData.setOutputLibraries(outputLibraries);
        return outputData;
    }

    public static class LibraryAndItsBooks {
        public Library library;
        public List<Book> books;
    }

}
