package org.jens.webforms.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jens Ritter on 08/09/2021.
 */
public class FNumberParserTest extends JsonParser<FNumber> {

    @Test
    @Override
    void testParse() throws JsonProcessingException {
        FNumber title = parseElement(new FNumber("title"));
        assertThat(title.getTitle()).isEqualTo("title");
        assertThat(title.getValue()).isNull();
    }

    @Test
    @Override
    void testParseValue() throws JsonProcessingException {
        FNumber title = parseElement(new FNumber("title").value(10.1D));
        assertThat(title.getDefaultValue()).isEqualTo(10.1D);
    }
}
