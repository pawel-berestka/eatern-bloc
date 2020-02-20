package com.eastern.block.algorithm;

import com.eastern.block.data.Library;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BestLibraryGetter {
    public Library getLibrary(List<Library> libraries) {
        Collections.sort(libraries, new AscendingSignupProcessTimeLibraryComparator());
        return libraries.get(0);
    }

    public class AscendingSignupProcessTimeLibraryComparator implements Comparator<Library> {
        @Override
        public int compare(Library o1, Library o2) {
            if (o1.getSignupProcessTime() < (o2.getSignupProcessTime()))
                return -1;
            else if (o1.getSignupProcessTime() > o2.getSignupProcessTime())
                return 1;
            else
                return 0;
        }
    }
}
