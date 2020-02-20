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

        List<LibraryAndItsBooks> result = new ArrayList<>();
        for (int i = 0; i < sortedLibrariesPerSingup.size(); ++i) {
            Library bestLibrary = sortedLibrariesPerSingup.get(i);
            if (timeLeft > bestLibrary.getSignupProcessTime()) {
                System.out.println(
                        String.format("Biblioteka o indeksie %s z czasem singup %s zmiesci sie, bo zostalo czasu %s.",
                                bestLibrary.getLibraryIndex().toString(), bestLibrary.getSignupProcessTime().toString(), timeLeft.toString()));

                List<Book> booksFromBestLibrary = getBooksToTake(bestLibrary.getBooks(),
                        chosenBooks,
                        getNumberOfBooksToTakeFromLibrary(timeLeft, bestLibrary));
                if (!booksFromBestLibrary.isEmpty()) {
                    System.out.println(String.format("Z biblioteki o indeksie %s wzielismy %s ksiazki %s", bestLibrary.getLibraryIndex(),
                            booksFromBestLibrary.size(),
                            booksFromBestLibrary));

                    LibraryAndItsBooks transformedBestLibrary = new LibraryAndItsBooks();
                    transformedBestLibrary.library = bestLibrary;
                    transformedBestLibrary.books = booksFromBestLibrary;
                    result.add(transformedBestLibrary);

                    chosenBooks.addAll(booksFromBestLibrary);
                    timeLeft -= bestLibrary.getSignupProcessTime();
                } else {
                    System.out.println("Heheszki, nic nie wyciagnales z biblioteki o indeksie " + bestLibrary.getLibraryIndex());
                }
            } else {
                System.out.println(
                        String.format("Biblioteka o indeksie %s z czasem singup %s sie nie zmiesci, bo zostalo czasu %s.",
                                bestLibrary.getLibraryIndex().toString(), bestLibrary.getSignupProcessTime().toString(), timeLeft.toString()));
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
        Integer books = (timeLeft - library.getSignupProcessTime()) * library.getShipmentSpeed();
        return books < 0 ? 0 : books;
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
                return -1;
            else if (o1.getSignupProcessTime() > o2.getSignupProcessTime())
                return 1;
            else
                return 0;
        }
    }
}
