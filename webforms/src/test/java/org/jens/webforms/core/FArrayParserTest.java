package org.jens.webforms.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Jens Ritter on 06/09/2021.
 */
class FArrayParserTest extends JsonParser<FArray> {


    @Test
    @Override
    void testParse() throws JsonProcessingException {
        FArray title = parseElement(new FArray("title"));
        assertThat(title.getTitle()).isEqualTo("title");
        assertThat(title.getDefaultValue()).isNull();
    }

    @Test
    @Override
    void testParseValue() {
        assertThrows(IllegalStateException.class, () -> {
            ElementSchema<List<String>> element = new FArray("title")
                .value(List.of("1"));
            FArray fArray = parseElement(element);
            assertThat(fArray.getDefaultValue()).isEqualTo(List.of("1"));
        });

    }
}
