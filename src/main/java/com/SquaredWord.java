package com;

/**
 * Created by Anna on 28.03.2017.
 */

import java.util.*;

public class SquaredWord {
    protected int dictionarySize;
    protected Vector<String> dictionary = new Vector<String>();
    protected Vector<Cell> squareMatrix = new Vector<Cell>();
    protected String prevLetter;
    private int row = 0;
    private int col = 0;

    SquaredWord() {
/*
        dictionarySize = 5;

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
*/
        dictionarySize = 6;

        dictionary.add("К");
        dictionary.add("Л");
        dictionary.add("И");
        dictionary.add("М");
        dictionary.add("А");
        dictionary.add("Т");

        squareMatrix.setSize(dictionarySize * dictionarySize);

        squareMatrix.set(dictionarySize * 0 + 0, new Cell("К", true));
        squareMatrix.set(dictionarySize * 0 + 1, new Cell("Л", true));
        squareMatrix.set(dictionarySize * 0 + 2, new Cell("И", true));
        squareMatrix.set(dictionarySize * 0 + 3, new Cell("М", true));
        squareMatrix.set(dictionarySize * 0 + 4, new Cell("А", true));
        squareMatrix.set(dictionarySize * 0 + 5, new Cell("Т", true));
        squareMatrix.set(dictionarySize * 3 + 2, new Cell("М", true));
        squareMatrix.set(dictionarySize * 3 + 3, new Cell("А", true));
        squareMatrix.set(dictionarySize * 3 + 4, new Cell("Т", true));
        squareMatrix.set(dictionarySize * 4 + 0, new Cell("Л", true));
        squareMatrix.set(dictionarySize * 4 + 1, new Cell("И", true));
        squareMatrix.set(dictionarySize * 4 + 2, new Cell("К", true));
    }

    private boolean CheckCol(String letter) {
        for (int i = 0; i < dictionarySize; ++i) {
            if (squareMatrix.get(dictionarySize * i + col) != null) {
                if (squareMatrix.get(dictionarySize * i + col).letter.equals(letter)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean CheckRow(String letter) {
        for (int i = 0; i < dictionarySize; ++i) {
            if (squareMatrix.get(dictionarySize * row + i) != null) {
                if (squareMatrix.get(dictionarySize * row + i).letter.equals(letter)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean CheckDiagonal(String letter) {
        if (col == row || ((dictionarySize - 1 - col) == row || (dictionarySize - 1 - row) == col)) {
            for (int i = 0; i < dictionarySize; ++i) {
                if (col == row) {
                    if (squareMatrix.get(dictionarySize * i + i) != null) {
                        if (squareMatrix.get(dictionarySize * i + i).letter.equals(letter)) {
                            return false;
                        }
                    }
                } else {
                    if (squareMatrix.get(dictionarySize * i + dictionarySize - i - 1) != null) {
                        if (squareMatrix.get(dictionarySize * i + dictionarySize - i - 1).letter.equals(letter)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public void RollBack() {
        if (squareMatrix.get(dictionarySize * row + col).isSpecified) {
            if (col == 0){
                --row;
                col = dictionarySize - 1;
            }
            else
                --col;
            RollBack();
        }
        for (String letter : dictionary) {
            if (squareMatrix.get(dictionarySize * row + col).letter.compareTo(letter) < 0) {
                if (CheckDiagonal(letter) && CheckCol(letter) && CheckRow(letter)) {
                    squareMatrix.set(dictionarySize * row + col, new Cell(letter, false));
                    return;
                }
            }
        }
        squareMatrix.set(dictionarySize * row + col, new Cell("", false));
        if (col == 0){
            --row;
            col = dictionarySize - 1;
        }
        else
            --col;
        RollBack();
    }

    private void AddLetter() {
        for (String letter : dictionary) {
            if (CheckDiagonal(letter) && CheckCol(letter) && CheckRow(letter)) {
                squareMatrix.set(dictionarySize * row + col, new Cell(letter, false));
                return;
            }
        }
        squareMatrix.set(dictionarySize * row + col, new Cell("", false));
        if (col == 0) {
            --row;
            col = dictionarySize - 1;
        }
        else
            --col;
        RollBack();
        return;
    }

    public void GreedyAlgoritm() {
        for (row = 1; row < dictionarySize; ++row) {
            for (col = 0; col < dictionarySize; ++col) {
                if (squareMatrix.get(dictionarySize * row + col) == null || squareMatrix.get(dictionarySize * row + col).letter.equals("") ) {
                    AddLetter();
                }
            }
            //PrintMatrix();
        }
    }

    public void PrintMatrix() {
        for (int i = 0; i < dictionarySize; ++i) {
            for (int j = 0; j < dictionarySize; ++j) {
                if (squareMatrix.get(dictionarySize * i + j) == null)
                    System.out.print("_ ");
                else
                    squareMatrix.get(dictionarySize * i + j).printCell();
            }
            System.out.println();
        }
    }

    public void PrintDictionary() {
        for (String letter : dictionary) {
            System.out.print(letter);
        }
        System.out.println();
    }
}
