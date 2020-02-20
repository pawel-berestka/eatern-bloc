package com.eastern.block.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputData {
    private Integer numberOfBooks = 0;
    private Integer numberOfLibraries = 0;
    private Integer daysForScannig = 0;
    private Map<Integer, Integer> booksScores = new HashMap<>();
    private List<Library> libraries = new ArrayList<>();

    public Integer getNumberOfBooks() {
        return numberOfBooks;
    }

    public void setNumberOfBooks(Integer numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }

    public Integer getNumberOfLibraries() {
        return numberOfLibraries;
    }

    public void setNumberOfLibraries(Integer numberOfLibraries) {
        this.numberOfLibraries = numberOfLibraries;
    }

    public Integer getDaysForScannig() {
        return daysForScannig;
    }

    public void setDaysForScannig(Integer daysForScannig) {
        this.daysForScannig = daysForScannig;
    }

    public Map<Integer, Integer> getBooksScores() {
        return booksScores;
    }

    public void setBooksScores(Map<Integer, Integer> booksScores) {
        this.booksScores = booksScores;
    }

    public List<Library> getLibraries() {
        return libraries;
    }

    public void setLibraries(List<Library> libraries) {
        this.libraries = libraries;
    }

    @Override
    public String toString() {
        return "InputData{" +
                "numberOfBooks=" + numberOfBooks +
                ", numberOfLibraries=" + numberOfLibraries +
                ", daysForScannig=" + daysForScannig +
                ", booksScores=" + booksScores +
                ", libraries=" + libraries +
                '}';
    }
}
