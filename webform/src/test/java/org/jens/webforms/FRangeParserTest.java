package org.jens.webforms;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jens Ritter on 08/09/2021.
 */
public class FRangeParserTest extends JsonParser<FRange> {
    @Test
    @Override
    void testParse() throws JsonProcessingException {
        FRange fRange = parseElement(new FRange("element"));
        assertThat(fRange.getTitle()).isEqualTo("element");
        assertThat(fRange.getDefaultValue()).isNull();
    }

    @Test
    @Override
    void testParseValue() throws JsonProcessingException {
        FRange fRange = parseElement(new FRange("element")
            .minimum(10).maximum(20)
            .formIndicator(true)
            .formStep(10)
        );
        assertThat(fRange.getMinimum()).isEqualTo(10);
        assertThat(fRange.getMaximum()).isEqualTo(20);
        assertThat(fRange.isFormIndicator()).isEqualTo(true);
        assertThat(fRange.getFormStep()).isEqualTo(10);
    }
}
