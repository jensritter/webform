package org.jens.webforms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Jens Ritter on 05/09/2021.
 */
class WebFormBuilderTest {

    WebFormBuilder form;

    @BeforeEach
    public void setUp() {
        form = new WebFormBuilder();
    }

    @Test
    void getForm() throws JsonProcessingException {
        form.add("name", new FString("Name"));
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(form.toWebForm())
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
        assertThat(form.toJson()).isEqualTo("{\"schema\":{\"name\":{\"type\":\"string\",\"title\":\"Name\"}},\"form\":[{\"key\":\"name\"},{\"type\":\"submit\",\"title\":\"Submit\"}]}");
    }

    @Test
    void addDuplicate() {
        form.add("name", new FString("label"));
        assertThrows(IllegalArgumentException.class, () -> form.add("name", new FDate("anotther")));
        assertThrows(IllegalArgumentException.class, () -> form.add("name", new FString("label")));
    }

    @Test
    void testAddBuilderPattern() {
        WebFormBuilder add = form.add("name", new FString("label"));
        assertThat(add.toJson()).isEqualTo(form.toJson());
    }


    @Test
    public void testBuilderIsNowJsonFaehig() throws JsonProcessingException {
        WebFormBuilder builder = new WebFormBuilder();
        builder.add("name", new FString("name"));
        builder.add("bool", new FBoolean("name").inlineTitle("inline"));

        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(builder);
        WebFormBuilder reread = objectMapper.readValue(s, WebFormBuilder.class);

        assertThat(reread.toJson()).isEqualTo(builder.toJson());
    }


}
