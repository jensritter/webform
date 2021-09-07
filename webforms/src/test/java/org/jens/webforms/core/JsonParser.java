package org.jens.webforms.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jens Ritter on 07/09/2021.
 */
public abstract class JsonParser<T extends ElementSchema<?>> {

    T parseElement(ElementSchema<?> origElement) throws JsonProcessingException {
        JsonForm jsonForm = new JsonForm();
        JsonFormParser parser = new JsonFormParser();

        jsonForm.add("element", origElement);
        Map<String, ElementSchema<?>> parsedMap = parser.parseElements(jsonForm.toString());

        assertThat(parsedMap).containsOnlyKeys("element");
        ElementSchema<?> element = parsedMap.get("element");

        return (T) element;
    }

    @Test
    abstract void testParse() throws JsonProcessingException;

    abstract void testParseValue() throws JsonProcessingException;
}
