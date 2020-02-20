package com.eastern.block.algorithm;

import com.eastern.block.data.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LibraryBooksGetter {
    public List<Book> getBooksToTake(List<Book> libraryBooks, Set<Book> chosenBooks, Integer numberOfBooks) {
        List<Book> booksToTake = new ArrayList<>();
        for (int i = libraryBooks.size() - 1; i >= 0; --i) {
            if (booksToTake.size() == numberOfBooks) {
                return booksToTake;
            }

            if (!chosenBooks.contains(libraryBooks.get(i))) {
                booksToTake.add(libraryBooks.get(i));
            }
        }

        return booksToTake;
    }
}
