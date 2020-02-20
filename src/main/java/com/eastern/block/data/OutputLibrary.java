package com.eastern.block.data;

import java.util.ArrayList;
import java.util.List;

public class OutputLibrary {
    private Integer libraryIndex = 0;
    private Integer numberOfBooksToSend = 0;
    private List<Integer> bookIndexesToSend = new ArrayList<>();

    public Integer getLibraryIndex() {
        return libraryIndex;
    }

    public void setLibraryIndex(Integer libraryIndex) {
        this.libraryIndex = libraryIndex;
    }

    public Integer getNumberOfBooksToSend() {
        return numberOfBooksToSend;
    }

    public void setNumberOfBooksToSend(Integer numberOfBooksToSend) {
        this.numberOfBooksToSend = numberOfBooksToSend;
    }

    public List<Integer> getBookIndexesToSend() {
        return bookIndexesToSend;
    }

    public void setBookIndexesToSend(List<Integer> bookIndexesToSend) {
        this.bookIndexesToSend = bookIndexesToSend;
    }

    @Override
    public String toString() {
        return "OutputLibrary{" +
                "libraryIndex=" + libraryIndex +
                ", numberOfBooksToSend=" + numberOfBooksToSend +
                ", bookIndexesToSend=" + bookIndexesToSend +
                '}';
    }
}
