package org.jens.webforms.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jens.webforms.core.ElementSchema.FormType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Jens Ritter on 07/09/2021.
 */
public class JsonFormParser {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Map<String, ElementSchema<?>> parseElements(String json) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(json);

        JsonNode schema = jsonNode.get("schema");
        JsonNode form = jsonNode.get("form");

        final List<JsonNode> formElements = new ArrayList<>();
        for(JsonNode formElement : form) {
            if(formElement.has("key")) {
                formElements.add(formElement);
            }
        }

        Iterator<JsonNode> formIterator = formElements.iterator();
        Map<String, ElementSchema<?>> result = new HashMap<>();
        for(JsonNode schemaElement : schema) {
            if(!formIterator.hasNext()) {
                throw new JsonFormParserException("Not enough form-elements for schema-elements present ");
            }
            JsonNode formElement = formIterator.next();

            ElementSchema<?> parsed = parse(schemaElement, formElement);
            result.put(formElement.get("key").asText(), parsed);
        }

        return result;
    }

    private ElementSchema<?> parse(JsonNode schemaElement, JsonNode formElement) throws JsonFormParserException {
        FormType formType = parseFormType(schemaElement);

        ElementSchema<?> elementSchema = generateType(schemaElement, formType);

        /*
        schemaElement
         _children = {LinkedHashMap@2606}  size = 5
            "type" -> {TextNode@2616} ""string""
            "title" -> {TextNode@2618} ""Welcome""
            "required" -> {BooleanNode@2620} "true"
            "description" -> {TextNode@2622} ""description""
            "default" -> {TextNode@2624} ""value""
        formElement
            "key" -> {TextNode@2640} ""welcome""
            "notitle" -> {BooleanNode@2620} "true"
            "disabled" -> {BooleanNode@2620} "true"
            "readonly" -> {BooleanNode@2620} "true"
            "htmlClass" -> {TextNode@2645} ""htmlclass""
            "fieldHtmlClass" -> {TextNode@2647} ""fieldhtml""
            "prepend" -> {TextNode@2649} ""prepend""
            "append" -> {TextNode@2651} ""append""
            "placeholder" -> {TextNode@2653} ""placeholder""
         */
        elementSchema.setTitle(getValueAsString(schemaElement, "title"));
        elementSchema.setRequired(getValueAsBoolean(schemaElement, "required"));
        elementSchema.setDescription(getValueAsString(schemaElement, "description"));
        elementSchema.setDefaultValue(getValueAsString(schemaElement, "default"));


        elementSchema.setNotitle(getValueAsBoolean(formElement, "notitle"));
        elementSchema.setDisabled(getValueAsBoolean(formElement, "disabled"));
        elementSchema.setReadonly(getValueAsBoolean(formElement, "readonly"));
        elementSchema.setFieldHtmlClass(getValueAsString(formElement, "fieldHtmlClass"));
        elementSchema.setHtmlClass(getValueAsString(formElement, "htmlClass"));
        elementSchema.setPrepend(getValueAsString(formElement, "prepend"));
        elementSchema.setAppend(getValueAsString(formElement, "append"));
        elementSchema.setPlaceholder(getValueAsString(formElement, "placeholder"));

        return elementSchema;
    }

    private boolean getValueAsBoolean(JsonNode schemaElement, String key) {
        JsonNode entry = schemaElement.get(key);
        if(entry == null) {
            return false;
        }
        return entry.asBoolean();
    }

    private static final @Nullable String getValueAsString(JsonNode node, String key) {
        JsonNode jsonNode = node.get(key);
        if(jsonNode != null) {
            return jsonNode.asText();
        }
        return null;
    }

    @NotNull
    private ElementSchema<?> generateType(JsonNode schemaElement, FormType formType) {
        JsonNode title = schemaElement.get("title");

        switch(formType) {
        case FormString:
            return new FString(title.asText());
        case FormArray:
            return new FArray(title.asText());
        case FormBoolean:
            return new FBoolean(title.asText());
        case FormInteger:
            return new FInteger(title.asText());
        case FormNumber:
            return new FNumber(title.asText());
        case FormObject:
            throw new IllegalStateException("unimplemented: 'object'");
        default:
            throw new IllegalStateException("unimplemented: " + formType);
        }
    }

    private FormType parseFormType(JsonNode schemaElement) throws JsonFormParserException {
        String type = schemaElement.get("type").asText();
        for(FormType value : FormType.values()) {
            if(value.toString().equals(type)) {
                return value;
            }
        }
        throw new JsonFormParserException("Unknown type : " + schemaElement);
    }

    public static final class JsonFormParserException extends JsonProcessingException {
        private static final long serialVersionUID = -5996718743988131086L;

        public JsonFormParserException(String msg) {
            super(msg);
        }
    }

    private static final class NumberedFormElement {
        private final int index;
        private final JsonNode node;

        private NumberedFormElement(int index, JsonNode node) {
            this.index = index;
            this.node = node;
        }
    }
}
