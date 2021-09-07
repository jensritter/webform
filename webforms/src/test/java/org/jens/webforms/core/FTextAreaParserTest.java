package org.jens.webforms.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jens Ritter on 08/09/2021.
 */
public class FTextAreaParserTest extends JsonParser<FTextArea> {
    @Test
    @Override
    void testParse() throws JsonProcessingException {
        FTextArea fTextArea = parseElement(new FTextArea("title"));
        assertThat(fTextArea.getTitle()).isEqualTo("title");
        assertThat(fTextArea.getValue()).isNull();
    }

    @Test
    @Override
    void testParseValue() throws JsonProcessingException {
        FTextArea fTextArea = parseElement(new FTextArea("title").value("20"));
        assertThat(fTextArea.getValue()).isEqualTo("20");
    }
}
