package ru.itits;

import java.math.BigDecimal;

public class Test {
    Object object = new Object();

    public Object getObject() {
        synchronized (object) {
            return object;
        }
    }
}
