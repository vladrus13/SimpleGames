package ru.vladrus13.RPG.core.utils;

import java.io.PrintStream;

/**
 * Sound library like to write some info to System.out. It's mark this problem
 */
public class EmptyStream extends PrintStream {
    /**
     * Constructor for class
     */
    public EmptyStream() {
        super(System.out);
    }

    @Override
    public void write(int b) {
        // ignore
    }

    @Override
    public void println(String s) {
        // ignore
    }

    /**
     * No ignore println for emptyStream
     *
     * @param s printed
     */
    public void noIgnorePrintln(String s) {
        super.println(s);
    }
}
