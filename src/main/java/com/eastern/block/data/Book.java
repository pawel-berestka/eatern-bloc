package com.eastern.block.data;

import java.util.Objects;

public class Book {
    public Integer index = 0;
    public Integer score = 0;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return index.equals(book.index) &&
                score.equals(book.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, score);
    }
}
