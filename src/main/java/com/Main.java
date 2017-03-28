package com;

/**
 * Created by Anna on 28.03.2017.
 */

import java.util.*;

public class Main {

    public static void main(String[] args) {
        SquaredWord sw = new SquaredWord();
        Collections.sort(sw.dictionary);
        sw.printDictionary();
        sw.greedyAlgorithm();
        sw.printMatrix();
    }
}
