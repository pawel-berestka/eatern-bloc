package com.eastern.block.algorithm;

import com.eastern.block.LibraryScoringGetter;
import com.eastern.block.data.InputData;
import com.eastern.block.data.Library;
import com.eastern.block.data.OutputData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Algorithm {

    private LibraryScoringGetter libraryScoringGetter;
    private Integer daysLeftOnStart;

    public Algorithm(LibraryScoringGetter libraryScoringGetter) {
        this.libraryScoringGetter = libraryScoringGetter;
    }

    public OutputData calculate(InputData inputData) {
        List<Library> libraryList = inputData.getLibraries();
        Set<Integer> scanned = new HashSet<>();
        daysLeftOnStart = inputData.getDaysForScannig();

        int daysLeft = daysLeftOnStart;
        for (Library library : libraryList) {
            Integer libraryScore = libraryScoringGetter.getScore(library, daysLeft, scanned);

            daysLeft--;
        }

    }

    private Library getLibraryToScanning(List<Library> ){
        return
    }
}
