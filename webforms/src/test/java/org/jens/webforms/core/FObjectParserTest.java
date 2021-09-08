package org.jens.webforms.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Jens Ritter on 08/09/2021.
 */
public class FObjectParserTest extends JsonParser<FObject> {


    @Test
    @Override
    void testParse() {
        assertThrows(IllegalStateException.class, () -> {
            FObject title = parseElement(new FObject("title"));
            assertThat(title.getTitle()).isEqualTo("title");
            assertThat(title.getDefaultValue()).isNull();
        });

    }

    @Test
    @Override
    void testParseValue() {
        assertThrows(IllegalStateException.class, () -> {
            FObject fRange = parseElement(new FObject("element"));
            assertThat(fRange).isNull();
        });

    }
}
