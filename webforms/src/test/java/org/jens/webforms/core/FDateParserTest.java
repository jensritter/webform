package org.jens.webforms.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jens Ritter on 08/09/2021.
 */
public class FDateParserTest extends JsonParser<FDate> {
    @Test
    @Override
    void testParse() throws JsonProcessingException {
        FDate fDate = parseElement(new FDate("title"));
        assertThat(fDate.getTitle()).isEqualTo("title");
        assertThat(fDate.getValue()).isNull();
    }

    @Test
    @Override
    void testParseValue() throws JsonProcessingException {
        LocalDate of = LocalDate.of(2021, 12, 31);
        FDate fDate = parseElement(new FDate("title").value(of));
        assertThat(fDate.getValue()).isEqualTo(of);
    }

}
