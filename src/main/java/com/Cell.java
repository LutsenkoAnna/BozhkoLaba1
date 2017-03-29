package com;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 * Created by Anna on 28.03.2017.
 */

//TODO: add coordinates of cell
public class Cell {
    protected String letter = new String();
    protected boolean isSpecified = false;

    Cell(String l, boolean b) {
        letter = l;
        isSpecified = b;
    }

    public void setLetter(String l) {
        letter = l;
    }

    public void printCell() {
        System.out.print(letter + " ");
    }
}
