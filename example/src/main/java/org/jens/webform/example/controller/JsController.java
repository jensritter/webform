package org.jens.webform.example.controller;

import org.jens.webforms.FBoolean;
import org.jens.webforms.FComboBox;
import org.jens.webforms.FDate;
import org.jens.webforms.FString;
import org.jens.webforms.WebForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDate;
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

    @ModelAttribute("webform")
    public WebForm json() {
        List<String> orte = Arrays.asList(
            "Hannover", "Goslar", "Bad Tölz", "Hamburg"
        );


        WebForm response = new WebForm();
        response.setTitleSubmit("Submit");
        response.add("name", new FString("Name").value("Jens").description("description-2").required(true).placeholder("Enter your Name"));
        response.add("vorname", new FString("Vorname").description("description-3"));
        response.add("strasse", new FString("Straße").description("description-4"));
        response.add("birthday", new FDate("Geburtstag").description("description-5")
            .value(LocalDate.of(2021, 12, 31))
        );
        response.add("heute", new FBoolean("Ist es heute", "ja/nein").description("description-6"));
        response.add("ort", new FComboBox("Orte", orte).description("description-1").value("1"));

        response.addButton("btnCancel", "Cancel");
        return response;
    }

}
