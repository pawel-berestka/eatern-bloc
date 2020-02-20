package com.eastern.block.file;

import com.eastern.block.data.Book;
import com.eastern.block.data.InputData;
import com.eastern.block.data.Library;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class InputDataReader {
    public InputData readData(String path) throws FileNotFoundException {
        InputData inputData = new InputData();
        File file = new File(path);
        Scanner fileScanner = new Scanner(file);

        String firstLine = fileScanner.nextLine().replace("\n", "");
        String[] firstLineParsed = firstLine.split(" ");

        inputData.setNumberOfBooks(Integer.valueOf(firstLineParsed[0]));
        inputData.setNumberOfLibraries(Integer.valueOf(firstLineParsed[1]));
        inputData.setDaysForScannig(Integer.valueOf(firstLineParsed[2]));

        String secondLine = fileScanner.nextLine().replace("\n", "");
        String[] secondLineParsed = secondLine.split(" ");
        Map<Integer, Book> books = new HashMap<>();
        Map<Integer, Integer> booksScores = new HashMap<>();
        for (int i = 0; i < inputData.getNumberOfBooks(); ++i) {
            booksScores.put(i, Integer.valueOf(secondLineParsed[i]));
            Book book = new Book(i, Integer.valueOf(secondLineParsed[i]));
            books.put(i, book);
        }
//        System.out.println(books);
        inputData.setBooksScores(booksScores);

        List<Library> libraries = new ArrayList<>();
        for (int i = 0; i < inputData.getNumberOfLibraries(); ++i) {
            String firstLibraryLine = fileScanner.nextLine().replace("\n", "");
            String secondLibraryLine = fileScanner.nextLine().replace("\n", "");

            String[] firstLibraryLineParsed = firstLibraryLine.split(" ");
            String[] secondLibraryLineParsed = secondLibraryLine.split(" ");

            Library library = new Library();
            library.setLibraryIndex(i);
            library.setNumberOfBooks(Integer.valueOf(firstLibraryLineParsed[0]));
            library.setSignupProcessTime(Integer.valueOf(firstLibraryLineParsed[1]));
            library.setShipmentSpeed(Integer.valueOf(firstLibraryLineParsed[2]));

            List<Book> libraryBooks = new ArrayList<>();
            for (int k = 0; k < library.getNumberOfBooks(); ++k) {
                Integer bookIndex = Integer.valueOf(secondLibraryLineParsed[k]);
                libraryBooks.add(books.get(bookIndex));
            }

            Collections.sort(libraryBooks, new BookComparator());
            library.setBooks(libraryBooks);
            libraries.add(library);
//            System.out.println(library);
        }

        inputData.setLibraries(libraries);
        return inputData;
    }

    public class BookComparator implements Comparator<Book> {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getScore().compareTo(o2.getScore());
        }
    }
}
