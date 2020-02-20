package com.eastern.block.data;

import java.util.ArrayList;
import java.util.List;

public class OutputData {
    private Integer numberOfLibraries = 0;
    private List<OutputLibrary> outputLibraries = new ArrayList<>();

    public Integer getNumberOfLibraries() {
        return numberOfLibraries;
    }

    public void setNumberOfLibraries(Integer numberOfLibraries) {
        this.numberOfLibraries = numberOfLibraries;
    }

    public List<OutputLibrary> getOutputLibraries() {
        return outputLibraries;
    }

    public void setOutputLibraries(List<OutputLibrary> outputLibraries) {
        this.outputLibraries = outputLibraries;
    }

    @Override
    public String toString() {
        return "OutputData{" +
                "numberOfLibraries=" + numberOfLibraries +
                ", outputLibraries=" + outputLibraries +
                '}';
    }
}
