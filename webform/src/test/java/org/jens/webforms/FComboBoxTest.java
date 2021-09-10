package org.jens.webforms;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Jens Ritter on 06/09/2021.
 */
class FComboBoxTest extends JsonTester {


    FComboBox element;

    @BeforeEach
    public void setupElement() {
        element = new FComboBox("label");
    }

    @Override
    @Test
    public void testJson() throws JsonProcessingException {
        assertThat(toSchemaJson(element)).isEqualTo(PLAIN_SCHEMA);
        assertThat(toFormJson(element)).isEqualTo(PLAIN_FORM);
    }

    @Test
    void getEnums() throws JsonProcessingException {
        element.selectionValue("10", "wert");
        assertThat(toSchemaJson(element)).isEqualTo("{\"type\":\"string\",\"title\":\"label\",\"enum\":[\"10\"]}");
        assertThat(toFormJson(element)).isEqualTo("{\"key\":\"name\",\"titleMap\":{\"10\":\"wert\"}}");
    }

    @Test
    void value() throws JsonProcessingException {
        element.selectionValue("10", "wert")
            .value("10");
        assertThat(toSchemaJson(element)).isEqualTo("{\"type\":\"string\",\"title\":\"label\",\"default\":\"10\",\"enum\":[\"10\"]}");
        assertThat(toFormJson(element)).isEqualTo("{\"key\":\"name\",\"titleMap\":{\"10\":\"wert\"}}");

    }

    @Test
    public void testContructor() {
        FComboBox label = new FComboBox("label");
        assertThat(label.getSelectionValues()).isEmpty();
        assertThat(label.getTitle()).isEqualTo("label");
        assertThat(label.getDefaultValue()).isNull();
    }

    @Test
    public void testConstructorStringCollection() {
        FComboBox label = new FComboBox("label", Arrays.asList("one", "two"));
        assertThat(label.getTitle()).isEqualTo("label");
        assertThat(label.getDefaultValue()).isNull();

        assertThat(label.getSelectionValues()).containsOnlyKeys("0", "1");
        assertThat(label.getSelectionValues()).containsExactlyInAnyOrderEntriesOf(Map.of("0", "one", "1", "two"));
        assertThat(label.getSelectionValues()).containsOnlyKeys("0", "1");
    }

    @Test
    public void testConstructorStringMap() {
        FComboBox label = new FComboBox("label", Map.of("one", "haus", "two", "auto"));
        assertThat(label.getTitle()).isEqualTo("label");
        assertThat(label.getDefaultValue()).isNull();

        assertThat(label.getSelectionValues()).containsExactlyEntriesOf(Map.of("one", "haus", "two", "auto"));
        assertThat(label.getSelectionValues()).containsOnlyKeys("one", "two");
    }

    @Test
    void setSelectionValues() {
        element.setSelectionValuesWithIndex(Arrays.asList("one", "two"));
        assertThat(element.getSelectionValues()).containsAllEntriesOf(Map.of("0", "one", "1", "two"));
    }

    @Test
    void testSetSelectionValues() {
        element.setSelectionValues(Map.of("one", "haus", "two", "auto"));
        assertThat(element.getSelectionValues()).containsExactlyEntriesOf(Map.of("one", "haus", "two", "auto"));
    }

    @Test
    void addSelectionValue() {
        element.selectionValue("one", "haus").selectionValue("two", "auto");
        assertThat(element.getSelectionValues()).containsExactlyInAnyOrderEntriesOf(Map.of("one", "haus", "two", "auto"));
    }

    @Test
    void isViewAsRadios() throws JsonProcessingException {
        element.selectionValue("one", "haus").selectionValue("two", "auto")
            .viewAsRadios(true);
        assertTrue(element.isViewAsRadios());
        assertThat(toSchemaJson(element)).isEqualTo("{\"type\":\"string\",\"title\":\"label\",\"enum\":[\"one\",\"two\"]}");
        assertThat(toFormJson(element)).isEqualTo("{\"type\":\"radios\",\"key\":\"name\",\"titleMap\":{\"two\":\"auto\",\"one\":\"haus\"}}");
    }

}
