package com.eastern.block.file;

import com.eastern.block.data.OutputData;

public class OutputDataWriter {
    public void writeData(String pathToOutputFile, OutputData outputData) {
        /*
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(pathToOutputFile));
            bw.write(outputData.getTypesOfPizzas() + "\n");
            bw.write(Stream.of(outputData.getPizzasIndexes()).map(Object::toString).collect(Collectors.joining(" ")));
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

         */
    }
}
