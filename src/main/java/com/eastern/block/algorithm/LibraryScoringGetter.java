package com.eastern.block.algorithm;

import com.eastern.block.data.Book;
import com.eastern.block.data.Library;

import java.util.List;
import java.util.Set;

public class LibraryScoringGetter {
    public Integer getScore(Library library, Integer deysLeft, Set<Integer> scanned) {
        Integer signupProcessTime = library.getSignupProcessTime();
        List<Book> books = library.getBooks();

        int maxNumberOfBooksPossibleToSend = (deysLeft - signupProcessTime) * library.getShipmentSpeed();
        int limit = Math.min(maxNumberOfBooksPossibleToSend, library.getNumberOfBooks());
        int score = books.stream().filter(book -> !scanned.contains(book.getIndex())).limit(limit).mapToInt(Book::getScore).sum();

        return score;
    }
}
