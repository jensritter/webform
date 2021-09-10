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

    <K extends ElementSchema<?>> K reconvert(K element) throws JsonProcessingException {
        WebFormBuilder name = new WebFormBuilder().add("name", element);
        String s = objectMapper.writeValueAsString(name);
        WebFormBuilder builder = objectMapper.readValue(s, WebFormBuilder.class);
        ElementSchema<?> next = builder.getElements().values().iterator().next();
        return (K) next;

    }

    abstract void testJson() throws JsonProcessingException;


}
