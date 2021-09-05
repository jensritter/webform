package org.jens.testing.forms.controller;

import org.jens.testing.forms.controller.form.FBoolean;
import org.jens.testing.forms.controller.form.FComboBox;
import org.jens.testing.forms.controller.form.FDate;
import org.jens.testing.forms.controller.form.FString;
import org.jens.testing.forms.controller.form.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Jens Ritter on 29/08/2021.
 */
@Controller
public class ExampleController {

    @GetMapping("/example")
    public String index() {
        return "example";
    }

    @GetMapping("/api/form")
    @ResponseBody
    public JsonResponse schema() {
        FComboBox orte = new FComboBox("Orte");
        orte.addValues(Arrays.asList(
            "Hannover", "Goslar", "Bad Tölz", "Hamburg"
        ));

        JsonResponse response = new JsonResponse();
        response.add("name", new FString("Name", true));
        response.add("vorname", new FString("Vorname"));
        response.add("strasse", new FString("Straße"));
        response.add("birthday", new FDate("Geburtstag"));
        response.add("heute", new FBoolean("Ist es heute", "ja/nein"));
        response.add("ort", orte);

        return response;
    }


    private final Logger logger = LoggerFactory.getLogger(ExampleController.class);

    @PostMapping("/api/form")
    public void postMapping(@RequestBody Map<String, String> body) {
        logger.info("{}", body);
    }
}
