package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");

        boolean bool = true;
        byte b = 8;
        char c = 'h';
        short s = 200;
        int i = 30000;
        long l = 9L;
        float f = .1F;
        double d = .1D;
        LOG.debug("boolean: {}, byte: {}, char: {}, short: {}, int: {}, long: {}, float: {}, double: {}",
                bool, b, c, s, i, l, f, d);
    }
}