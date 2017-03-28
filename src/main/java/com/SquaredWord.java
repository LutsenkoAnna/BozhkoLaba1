package com;

/**
 * Created by Anna on 28.03.2017.
 */

import java.util.*;

public class SquaredWord {
    protected int dictionarySize = 5;
    protected Vector<String> dictionary = new Vector<String>(dictionarySize);
    protected Vector<Cell> squareMatrix = new Vector<Cell>(dictionarySize * dictionarySize);
    protected int countOfRollBack = 0;

    SquaredWord() {
        dictionary.add("С");
        dictionary.add("Л");
        dictionary.add("Е");
        dictionary.add("З");
        dictionary.add("А");

        squareMatrix.setSize(dictionarySize * dictionarySize);

        squareMatrix.set(dictionarySize * 0 + 0, new Cell("С", true));
        squareMatrix.set(dictionarySize * 0 + 1, new Cell("Л", true));
        squareMatrix.set(dictionarySize * 0 + 2, new Cell("Е", true));
        squareMatrix.set(dictionarySize * 0 + 3, new Cell("З", true));
        squareMatrix.set(dictionarySize * 0 + 4, new Cell("А", true));
        squareMatrix.set(dictionarySize * 2 + 2, new Cell("Л", true));
        squareMatrix.set(dictionarySize * 2 + 3, new Cell("Е", true));
        squareMatrix.set(dictionarySize * 2 + 4, new Cell("С", true));
    }

    private boolean checkVertical(String letter, int verticalIndex) {
        for (int i = 0; i < dictionarySize; ++i) {
            if (squareMatrix.get(dictionarySize * i + verticalIndex) != null) {
                if (squareMatrix.get(dictionarySize * i + verticalIndex).letter.equals(letter)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkHorizontal(String letter, int horizontalIndex) {
        for (int i = 0; i < dictionarySize; ++i) {
            if (squareMatrix.get(dictionarySize * horizontalIndex + i) != null) {
                if (squareMatrix.get(dictionarySize * horizontalIndex + i).letter.equals(letter)) {
                    return false;
                }
            }
        }
        return true;
    }

    //TODO: Здесь опущение, что достаточно изменить одну букву
    public void rollBack(int verticalIndex, int horizontalIndex, String dictionaryLetter){
        System.out.println(dictionaryLetter + " i = " + horizontalIndex + " j = " + verticalIndex);
        for (String letter : dictionary) {
            if (letter.compareTo(dictionaryLetter) > 0) {
                if (letter.equals(squareMatrix.get(dictionarySize * horizontalIndex + verticalIndex).letter) || checkVertical(letter, verticalIndex) && checkHorizontal(letter, horizontalIndex)) {
                    squareMatrix.get(dictionarySize * horizontalIndex + verticalIndex).setLetter(letter);
                    return;
                }
            }
        }
        ++countOfRollBack;
    }


    public void greedyAlgorithm() {
        for (int i = 1; i < dictionarySize; ++i) {
            for (int j = 0; j < dictionarySize; ++j) {
                if (squareMatrix.get(dictionarySize * i + j) == null || !squareMatrix.get(dictionarySize * i + j).isSpecified) {
                    for (int k = 0; k < dictionarySize; ++k) {
                        String letter = dictionary.get(k);
                        if (checkVertical(letter, j) && checkHorizontal(letter, i)) {
                            squareMatrix.set(dictionarySize * i + j, new Cell(letter, false));
                            break;
                        }
                        if (k == dictionarySize - 1) {
                            System.out.print(i + " " + j + " " + k);
                            rollBack(j - countOfRollBack - 1, i, squareMatrix.get(dictionarySize * i + (j - countOfRollBack - 1)).letter);
                            j -= countOfRollBack + 1;
                        }
                    }
                }
            }
        }
    }

    public void printMatrix() {
        for (int i = 0; i < dictionarySize; ++i) {
            for (int j = 0; j < dictionarySize; ++j) {
                squareMatrix.get(dictionarySize * i + j).printCell();
            }
            System.out.println();
        }
    }

    public void printDictionary() {
        for (String letter : dictionary) {
            System.out.print(letter);
        }
        System.out.println();
    }
}
