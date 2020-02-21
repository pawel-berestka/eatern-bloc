package com.eastern.block.algorithm;

import com.eastern.block.data.Book;
import com.eastern.block.data.Library;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class BestLibraryGetter {
    private LibraryScoreGetter libraryScoreGetter;

    public BestLibraryGetter(LibraryScoreGetter libraryScoreGetter) {
        this.libraryScoreGetter = libraryScoreGetter;
    }

    public Optional<Library> getLibrary(List<Library> libraries, Set<Book> alreadyChosenBooks, Integer timeLeft) {
        libraries.parallelStream().forEach(library -> library.setLibraryScore(libraryScoreGetter.getLibraryScore(library, alreadyChosenBooks, timeLeft)));
        return libraries.stream().max(Comparator.comparing(Library::getLibraryScore));
    }
}
