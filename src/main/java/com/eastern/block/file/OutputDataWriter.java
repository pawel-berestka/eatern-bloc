package com.eastern.block.file;

import com.eastern.block.data.OutputData;
import com.eastern.block.data.OutputLibrary;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.stream.Collectors;

public class OutputDataWriter {
    public void writeData(String pathToOutputFile, OutputData outputData) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(pathToOutputFile));
            bw.write(outputData.getNumberOfLibraries() + "\n");
            for (int i = 0; i < outputData.getNumberOfLibraries(); ++i) {
                OutputLibrary outputLibrary = outputData.getOutputLibraries().get(i);
                bw.write(outputLibrary.getLibraryIndex() + " " + outputLibrary.getNumberOfBooksToSend() + "\n");
                bw.write(outputLibrary.getBookIndexesToSend().stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(" ")));
                bw.write("\n");
            }
            bw.flush();
        } catch (Exception ex) {
            System.err.println("Err" + ex.getMessage());
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }

    }
}
