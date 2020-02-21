package com.eastern.block.algorithm;

import com.eastern.block.data.Book;
import com.eastern.block.data.Library;

import java.util.Set;

public class LibraryScoreGetter {
    private LibraryBooksGetter libraryBooksGetter;
    private NumberOfBooksGetter numberOfBooksGetter;

    public LibraryScoreGetter(LibraryBooksGetter libraryBooksGetter, NumberOfBooksGetter numberOfBooksGetter) {
        this.libraryBooksGetter = libraryBooksGetter;
        this.numberOfBooksGetter = numberOfBooksGetter;
    }

    public Integer getLibraryScore(Library library, Set<Book> alreadyChosenBooks, Integer timeLeft) {
        Integer totalSumFromBooks = libraryBooksGetter
                .getBooksToTake(library.getBooks(), alreadyChosenBooks, numberOfBooksGetter.getNumberOfBooksToTake(timeLeft, library.getSignupProcessTime(), library.getShipmentSpeed())).stream()
                .map(Book::getScore).mapToInt(Integer::intValue).sum();
        return totalSumFromBooks * (timeLeft) / (library.getSignupProcessTime());
    }
}
