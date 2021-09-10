package org.jens.webforms;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Pojo für JSON
 *
 * @author Jens Ritter on 10/09/2021.
 */
public final class WebForm {
    private final Map<String, JsonSchema> schema;
    private final List<JsonForm> jsonForm;

    public WebForm(Map<String, JsonSchema> schema, List<JsonForm> jsonForm) {
        this.schema = Collections.unmodifiableMap(schema);
        this.jsonForm = Collections.unmodifiableList(jsonForm);
    }

    public Map<String, JsonSchema> getSchema() {return schema;}

    public List<JsonForm> getForm() {return jsonForm;}
}
