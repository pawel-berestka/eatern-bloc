package com.eastern.block.data;

import java.util.List;

public class Library {
    private Integer numberOfBooks;
    private Integer signupProcessTime;
    private Integer shipmentSpeed;
    private List<Book> books;

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
                "numberOfBooks=" + numberOfBooks +
                ", signupProcessTime=" + signupProcessTime +
                ", shipmentSpeed=" + shipmentSpeed +
                ", books=" + books +
                '}';
    }
}
