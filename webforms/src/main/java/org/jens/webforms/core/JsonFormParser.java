package org.jens.webforms.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jens.webforms.core.ElementSchema.FormType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        ElementSchema<?> elementSchema = generateType(schemaElement, formElement, formType);

        // Generic Properties :
        JsonNode aDefault = schemaElement.get("default");
        elementSchema.applyDefaultFromJson(schemaElement, formElement);
        // Specific Properties :
        elementSchema.parseForm(schemaElement, formElement, Optional.ofNullable(aDefault));
        return elementSchema;
    }

    private final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(JsonFormParser.class);

    @NotNull
    private ElementSchema<?> generateType(JsonNode schemaElement, JsonNode formElement, FormType formType) {
        JsonNode titleNode = schemaElement.get("title");
        final String title;
        if(titleNode == null) {
            logger.info("No Title for Element {}", schemaElement);
            title = "";
        } else {
            title = titleNode.asText();
        }

        JsonNode type = formElement.get("type");
        switch(formType) {
        case FormString:
            if(type != null) {
                if("date".equals(type.asText())) {
                    return new FDate(title);
                }
                if("textarea".equals(type.asText())) {
                    return new FTextArea(title);
                }
            }
            if(formElement.has("titleMap")) {
                return new FComboBox(title);
            }
            return new FString(title);
        case FormArray:
            return new FArray(title);
        case FormBoolean:
            return new FBoolean(title);
        case FormInteger:
            if(type != null) {
                if("range".equals(type.asText())) {
                    return new FRange(title);
                }
            }
            return new FInteger(title);
        case FormNumber:
            return new FNumber(title);
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

}
