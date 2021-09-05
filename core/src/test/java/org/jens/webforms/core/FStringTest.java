package org.jens.webforms.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jens Ritter on 05/09/2021.
 */
class FStringTest {

    ObjectWriter objectWriter;

    @BeforeEach
    public void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
    }

    @Test
    public void testJson() throws JsonProcessingException {
        FString value = new FString("label");
        ElementForm form = new ElementForm("name");
        value.buildForm(form);


        String schema = objectWriter.writeValueAsString(value);
        assertThat(schema).isEqualTo("");

        String formJson = objectWriter.writeValueAsString(form);
        assertThat(formJson).isEqualTo("");
    }

}
