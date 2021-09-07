package org.jens.webforms.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jens Ritter on 06/09/2021.
 */
class FComboBoxParserTest extends JsonParser<FComboBox> {

    @Test
    @Override
    void testParse() throws JsonProcessingException {
        FComboBox title = parseElement(new FComboBox("title")
            .selectionValue("10", "welcome")
            .selectionValue("20", "welcome2")

        );
        assertThat(title.getTitle()).isEqualTo("title");
        assertThat(title.isViewAsRadios()).isFalse();
        assertThat(title.getSelectionValues()).containsAllEntriesOf(
            Map.of("10", "welcome", "20", "welcome2")
        );
        assertThat(title.getDefaultValue()).isNull();
    }

    @Test
    @Override
    void testParseValue() throws JsonProcessingException {
        FComboBox title = parseElement(new FComboBox("title")
            .selectionValue("10", "welcome")
            .selectionValue("20", "welcome2")
            .value("20")
        );

        assertThat(title.getDefaultValue()).isEqualTo("20");
    }

    @Test
    void comboBoxOhneWerteIstWieFString() throws JsonProcessingException {
        FComboBox box = new FComboBox("box");
        JsonForm jsonForm = new JsonForm();
        jsonForm.add("box", box);
        JsonFormParser parser = new JsonFormParser();
        Map<String, ElementSchema<?>> parsed = parser.parseElements(jsonForm.toString());
        assertThat(parsed).containsOnlyKeys("box");
        ElementSchema<?> box1 = parsed.get("box");
        assertThat(box1).isInstanceOf(FString.class);
    }

    @Test
    public void testviewAsRadios() throws JsonProcessingException {
        FComboBox box = parseElement(new FComboBox("title").selectionValue("10", "welcome").viewAsRadios(true));
        assertThat(box.isViewAsRadios()).isTrue();
    }
}
