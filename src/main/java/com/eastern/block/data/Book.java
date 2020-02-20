package com.eastern.block.data;

public class Book {
    public final Integer index;
    public final Integer score;

    public Book(Integer index, Integer score) {
        this.index = index;
        this.score = score;
    }

    public Integer getIndex() {
        return index;
    }

    public Integer getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Book{" +
                "index=" + index +
                ", score=" + score +
                '}';
    }
}
