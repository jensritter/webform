package org.jens.webforms.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jens Ritter on 08/09/2021.
 */
public class FStringParserTest extends JsonParser<FString> {

    @Test
    @Override
    void testParse() throws JsonProcessingException {
        FString fString = parseElement(new FString("title"));
        assertThat(fString.getTitle()).isEqualTo("title");
        assertThat(fString.getDefaultValue()).isNull();
    }

    @Test
    @Override
    void testParseValue() throws JsonProcessingException {
        FString fString = parseElement(new FString("title").value("20"));
        assertThat(fString.getValue()).isEqualTo("20");
    }
}
