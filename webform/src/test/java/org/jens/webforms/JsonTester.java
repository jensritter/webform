package org.jens.webforms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;

/**
 * @author Jens Ritter on 05/09/2021.
 */
public abstract class JsonTester {
    protected static ObjectMapper objectMapper;


    static final String PLAIN_FORM = "{\"key\":\"name\"}";
    static final String PLAIN_SCHEMA = "{\"type\":\"string\",\"title\":\"label\"}";


    @BeforeAll
    public static void setupObjectWriter() {
        objectMapper = new ObjectMapper();
    }

    String toSchemaJson(ElementSchema<?> obj) throws JsonProcessingException {
        JsonSchema test = new WebFormBuilder()
            .add("name", obj)
            .toWebForm()
            .getSchema()
            .values().iterator().next();

        return objectMapper.writeValueAsString(test).replace("\r", "");
    }

    String toFormJson(ElementSchema<?> item) throws JsonProcessingException {
        JsonForm test = new WebFormBuilder()
            .add("name", item)
            .toWebForm()
            .getForm()
            .iterator().next();
        return objectMapper.writeValueAsString(test).replace("\r", "");
    }

    abstract void testJson() throws JsonProcessingException;


}
