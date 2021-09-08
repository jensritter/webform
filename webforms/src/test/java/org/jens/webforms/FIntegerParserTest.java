package org.jens.webforms;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jens Ritter on 08/09/2021.
 */
public class FIntegerParserTest extends JsonParser<FInteger> {

    @Test
    @Override
    void testParse() throws JsonProcessingException {
        FInteger element = parseElement(new FInteger("element"));
        assertThat(element.getTitle()).isEqualTo("element");
        assertThat(element.getDefaultValue()).isNull();

    }

    @Test
    @Override
    void testParseValue() throws JsonProcessingException {
        FInteger element = parseElement(new FInteger("element").value(10));

        assertThat(element.getDefaultValue())
            .isInstanceOf(Integer.class)
            .isEqualTo(10);
    }
}
