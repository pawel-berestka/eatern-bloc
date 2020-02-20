package com.eastern.block.data;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private Integer libraryIndex = 0;
    private Integer numberOfBooks = 0;
    private Integer signupProcessTime = 0;
    private Integer shipmentSpeed = 0;
    private Integer libraryScore = 0;
    private Boolean isScaned = false;

    public Integer getLibraryScore() {
        return libraryScore;
    }

    public void setLibraryScore(Integer libraryScore) {
        this.libraryScore = libraryScore;
    }

    private List<Book> books = new ArrayList<>();

    public Integer getLibraryIndex() {
        return libraryIndex;
    }

    public void setLibraryIndex(Integer libraryIndex) {
        this.libraryIndex = libraryIndex;
    }

    public Integer getShipmentSpeed() {
        return shipmentSpeed;
    }

    public void setShipmentSpeed(Integer shipmentSpeed) {
        this.shipmentSpeed = shipmentSpeed;
    }

    public Integer getNumberOfBooks() {
        return numberOfBooks;
    }

    public void setNumberOfBooks(Integer numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }

    public Integer getSignupProcessTime() {
        return signupProcessTime;
    }

    public void setSignupProcessTime(Integer signupProcessTime) {
        this.signupProcessTime = signupProcessTime;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Library{" +
                "libraryIndex=" + libraryIndex +
                ", numberOfBooks=" + numberOfBooks +
                ", signupProcessTime=" + signupProcessTime +
                ", shipmentSpeed=" + shipmentSpeed +
                ", books=" + books +
                '}';
    }
}
