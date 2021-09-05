package org.jens.webforms.core.controller.form;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Jens Ritter on 05/09/2021.
 */
public class RandomValues {
    private static final AtomicInteger counter = new AtomicInteger(0);

    public static String randomDesc() {
        return "description-" + counter.incrementAndGet();
    }
}
