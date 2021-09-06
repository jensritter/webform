
package org.jens.webforms.core;

import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

/**
 * @author Jens Ritter on 29/08/2021.
 */
public class FDate extends ElementSchema<LocalDate> {

    /**
     * Formatter, welches von Chrome+Firefox als gültiges Datum anerkannt werden
     */
    public static final DateTimeFormatter DATE_YYYY_MM_DD = new DateTimeFormatterBuilder()
        .appendValue(ChronoField.YEAR, 4)
        .appendLiteral('-')
        .appendValue(ChronoField.MONTH_OF_YEAR, 2)
        .appendLiteral('-')
        .appendValue(ChronoField.DAY_OF_MONTH, 2)
        .toFormatter();

    public FDate(String label) {
        super(FormType.FormString, label);
    }

    @Override
    ElementForm buildForm(ElementForm element) {
        element.setType("date");
        return element;
    }

    @Override
    public ElementSchema<LocalDate> value(@Nullable LocalDate value) {
        setDefaultValue(value != null ? DATE_YYYY_MM_DD.format(value) : null);
        return this;
    }

}
