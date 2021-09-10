package org.jens.webforms;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jens Ritter on 07/09/2021.
 */
public abstract class JsonParser<T extends ElementSchema<?>> {

    T parseElement(ElementSchema<?> origElement) throws JsonProcessingException {
        WebFormBuilder webForm = new WebFormBuilder();
        WebFormParser parser = new WebFormParser();

        webForm.add("element", origElement);
        Map<String, ElementSchema<?>> parsedMap = parser.parseElements(webForm.toJson());

        assertThat(parsedMap).containsOnlyKeys("element");
        ElementSchema<?> element = parsedMap.get("element");

        return (T) element;
    }

    abstract void testParse() throws JsonProcessingException;

    abstract void testParseValue() throws JsonProcessingException;
}
