package com.eastern.block.algorithm;

import com.eastern.block.data.*;

import java.util.*;
import java.util.stream.Collectors;

public class Algorithm {
//    private LibraryScoringGetter libraryScoringGetter;
//    private Integer daysLeftOnStart;

    public OutputData calculate(InputData inputData) {
        List<Library> sortedLibrariesPerSingup = getSortedLibrariesPerSignup(inputData.getLibraries());

        Integer wholeTime = inputData.getDaysForScannig();
        Integer timeLeft = wholeTime;
        Set<Book> chosenBooks = new HashSet<>();
//        Map<Library, List<Book>> result = new HashMap<>();

        List<LibraryAndItsBooks> result = new ArrayList<>();
        for (int i = 0; i < sortedLibrariesPerSingup.size(); ++i) {
            Library bestLibrary = sortedLibrariesPerSingup.get(i);
            if (timeLeft > bestLibrary.getSignupProcessTime()) {
                List<Book> booksFromBestLibrary = getBooksToTake(bestLibrary.getBooks(),
                        chosenBooks,
                        getNumberOfBooksToTakeFromLibrary(timeLeft, bestLibrary));
//                result.put(bestLibrary, booksFromBestLibrary);

                LibraryAndItsBooks transformedBestLibrary = new LibraryAndItsBooks();
                transformedBestLibrary.library = bestLibrary;
                transformedBestLibrary.books = booksFromBestLibrary;
                result.add(transformedBestLibrary);

                timeLeft -= bestLibrary.getSignupProcessTime();
            } else {
                continue;
            }
        }


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

//    public Algorithm(LibraryScoringGetter libraryScoringGetter) {
//        this.libraryScoringGetter = libraryScoringGetter;
//    }

    private Integer getNumberOfBooksToTakeFromLibrary(Integer timeLeft, Library library) {
        return (timeLeft - library.getSignupProcessTime()) * library.getShipmentSpeed();
    }

    private List<Book> getBooksToTake(List<Book> libraryBooks, Set<Book> chosenBooks, Integer numberToBooksToTake) {
        List<Book> booksToTake = new ArrayList<>();
        for (int i = libraryBooks.size() - 1; i >= 0; --i) {
            if (booksToTake.size() == numberToBooksToTake) {
                return booksToTake;
            }

            if (!chosenBooks.contains(libraryBooks.get(i))) {
                booksToTake.add(libraryBooks.get(i));
            }
        }

        return booksToTake;
    }

    private List<Library> getSortedLibrariesPerSignup(List<Library> libraries) {
        Collections.sort(libraries, new LibraryComparator());
        return libraries;
    }

    public static class LibraryAndItsBooks {
        public Library library;
        public List<Book> books;
    }

    public class LibraryComparator implements Comparator<Library> {
        @Override
        public int compare(Library o1, Library o2) {
            if (o1.getSignupProcessTime() < (o2.getSignupProcessTime()))
                return 1;
            else if (o1.getSignupProcessTime() > o2.getSignupProcessTime())
                return -1;
            else
                return 0;
        }
    }
}
