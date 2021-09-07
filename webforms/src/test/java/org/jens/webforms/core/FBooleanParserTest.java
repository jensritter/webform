package org.jens.webforms.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jens Ritter on 07/09/2021.
 */
public class FBooleanParserTest extends JsonParser<FBoolean> {

    @Test
    @Override
    public void testParse() throws JsonProcessingException {
        FBoolean fBoolean = parseElement(new FBoolean("title"));
        assertThat(fBoolean.getTitle()).isEqualTo("title");
        assertThat(fBoolean.getDefaultValue()).isNull();
    }

    @Test
    @Override
    void testParseValue() throws JsonProcessingException {
        FBoolean fBoolean = parseElement(new FBoolean("Boolean").value(true));
        assertThat(fBoolean.getDefaultValue()).isEqualTo(Boolean.TRUE);
    }

    @Test
    public void testParseValueFalse() throws JsonProcessingException {
        FBoolean fBoolean = parseElement(new FBoolean("Boolean").value(false));
        assertThat(fBoolean.getDefaultValue()).isEqualTo(Boolean.FALSE);
    }


    @Test
    public void testInlineTitle() throws JsonProcessingException {
        FBoolean fBoolean = parseElement(new FBoolean("Boolean").inlineTitle("inlinetittle"));
        assertThat(fBoolean.getInlineTitle()).isEqualTo("inlinetittle");
    }


}
