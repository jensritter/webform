package org.jens.webforms.example.controller;

import org.jens.webforms.core.FBoolean;
import org.jens.webforms.core.FComboBox;
import org.jens.webforms.core.FDate;
import org.jens.webforms.core.FString;
import org.jens.webforms.core.JsonForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;
import java.util.List;

/**
 * @author Jens Ritter on 05/09/2021.
 */
@Controller
public class JsController {

    @GetMapping("/json")
    public String index() {
        return "json";
    }

    @ModelAttribute("jsonform")
    public JsonForm json() {
        List<String> orte = Arrays.asList(
            "Hannover", "Goslar", "Bad Tölz", "Hamburg"
        );


        JsonForm response = new JsonForm();
        response.setTitleSubmit("Submit");
        response.add("name", new FString("Name").setValue("Jens").setDescription("description-2").setRequired(true).setPlaceholder("Enter your Name"));
        response.add("vorname", new FString("Vorname").setDescription("description-3"));
        response.add("strasse", new FString("Straße").setDescription("description-4"));
        response.add("birthday", new FDate("Geburtstag").setDescription("description-5"));
        response.add("heute", new FBoolean("Ist es heute", "ja/nein").setDescription("description-6"));
        response.add("ort", new FComboBox("Orte", orte).setDescription("description-1").setValue("1"));

        return response;
    }

}
