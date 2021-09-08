package org.jens.webforms.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author Jens Ritter on 05/09/2021.
 */
class JsonFormTest {

    JsonForm form;

    @BeforeEach
    public void setUp() {
        form = new JsonForm();
    }

    @Test
    void getForm() throws JsonProcessingException {
        form.add("name", new FString("Name"));
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(form)
            .replace("\r", "");
        assertThat(json).isEqualTo("{\n" +
            "  \"schema\" : {\n" +
            "    \"name\" : {\n" +
            "      \"type\" : \"string\",\n" +
            "      \"title\" : \"Name\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"form\" : [ {\n" +
            "    \"key\" : \"name\"\n" +
            "  }, {\n" +
            "    \"type\" : \"submit\",\n" +
            "    \"title\" : \"Submit\"\n" +
            "  } ]\n" +
            "}");
    }

    @Test
    public void testToString() {
        form.add("name", new FString("Name"));
        assertThat(form.toString()).isEqualTo("{\"schema\":{\"name\":{\"type\":\"string\",\"title\":\"Name\"}},\"form\":[{\"key\":\"name\"},{\"type\":\"submit\",\"title\":\"Submit\"}]}");
    }

    @Test
    void addDuplicate() {
        form.add("name", new FString("label"));
        assertThrows(IllegalArgumentException.class, () -> form.add("name", new FDate("anotther")));
        assertThrows(IllegalArgumentException.class, () -> form.add("name", new FString("label")));
    }


    @Test
    void getSchema() {
        FString mock = mock(FString.class);
        form.add("item", mock);
        List<ElementFormAbstract> form = this.form.getForm();

        verify(mock).buildForm(any(ElementForm.class));
    }


}
