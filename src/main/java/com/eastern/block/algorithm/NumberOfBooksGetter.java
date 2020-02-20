package com.eastern.block.algorithm;

public class NumberOfBooksGetter {
    public Integer getNumberOfBooksToTake(Integer timeLeft, Integer signupProcessTime, Integer shipmentSpeed) {
        Integer books = (timeLeft - signupProcessTime) * shipmentSpeed;
        return books < 0 ? 0 : books;
    }
}
