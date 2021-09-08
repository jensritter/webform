package org.jens.webforms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeAll;

/**
 * @author Jens Ritter on 05/09/2021.
 */
public abstract class JsonTester {
    protected static ObjectWriter objectWriter;


    static final String PLAIN_FORM = "{\"key\":\"name\"}";
    static final String PLAIN_SCHEMA = "{\"type\":\"string\",\"title\":\"label\"}";


    @BeforeAll
    public static void setupObjectWriter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectWriter = objectMapper.writer();
    }

    String toJson(Object obj) throws JsonProcessingException {
        return objectWriter.writeValueAsString(obj).replace("\r", "");
    }

    String toFormJson(ElementSchema<?> item) throws JsonProcessingException {
        ElementForm form = new ElementForm("name");
        item.buildDefaultForm(form);
        item.buildForm(form);
        return toJson(form);
    }

    abstract void testJson() throws JsonProcessingException;


}
