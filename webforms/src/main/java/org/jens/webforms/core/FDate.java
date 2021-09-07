
package org.jens.webforms.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

/**
 * @author Jens Ritter on 29/08/2021.
 */
public class FDate extends ElementSchema<LocalDate> {
    private final Logger logger = LoggerFactory.getLogger(FDate.class);

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
    void buildForm(ElementForm element) {
        element.setType("date");
    }

    @Override
    public void parseForm(JsonNode schemaElement, JsonNode formElement, Optional<JsonNode> defaultValue) {
        defaultValue.ifPresent(k -> {
            try {
                TemporalAccessor parse = DATE_YYYY_MM_DD.parse(k.asText());
                setValue(LocalDate.from(parse));
            } catch(DateTimeParseException e) {
                logger.warn("Cannot parse Date-Value for {}", schemaElement);
            }
        });
    }

    //
    // beans
    //

    @JsonIgnore
    public @Nullable LocalDate getValue() {
        Object defaultValue = getDefaultValue();
        if(defaultValue == null) {return null;}

        String stringValue = (String) defaultValue;
        TemporalAccessor parse = DATE_YYYY_MM_DD.parse(stringValue);
        return LocalDate.from(parse);
    }

    public void setValue(@Nullable LocalDate date) {
        if(date == null) {
            setDefaultValue(null);
        } else {
            setDefaultValue(DATE_YYYY_MM_DD.format(date));
        }
    }

    //
    // builds
    //
    @Override
    public ElementSchema<LocalDate> value(@Nullable LocalDate value) {
        setValue(value);
        return this;
    }


}
