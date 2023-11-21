package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row;
    private int column;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    private void iterateRows() {
        while (row < data.length && column >= data[row].length) {
            row++;
            column = 0;
        }
    }

    @Override
    public boolean hasNext() {
        iterateRows();
        return row < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        var rsl = data[row][column++];
        iterateRows();
        return rsl;
    }
}