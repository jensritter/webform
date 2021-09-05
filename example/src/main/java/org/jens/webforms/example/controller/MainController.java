package org.jens.webforms.example.controller;


import org.jens.webforms.core.controller.form.FBoolean;
import org.jens.webforms.core.controller.form.FComboBox;
import org.jens.webforms.core.controller.form.FDate;
import org.jens.webforms.core.controller.form.FString;
import org.jens.webforms.core.controller.form.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

/**
 * @author Jens Ritter on 29/08/2021.
 */
@Controller
public class MainController {
    private final Logger logger = LoggerFactory.getLogger(MainController.class);


    @GetMapping("/main")
    public String index() {
        return "index";
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

    @PostMapping("/api/form")
    @ResponseBody
    public FormObject postMapping(FormObject obj, BindingResult bindingResult) {
        logger.info("{}", obj);

        return obj;
    }

    public static class FormObject {
        private String name;
        private String vorname;
        private String strasse;
        private String birthday;
        private int ort;

        public String getName() {return name;}

        public void setName(String name) {this.name = name;}

        public String getVorname() {return vorname;}

        public void setVorname(String vorname) {this.vorname = vorname;}

        public String getStrasse() {return strasse;}

        public void setStrasse(String strasse) {this.strasse = strasse;}

        public String getBirthday() {return birthday;}

        public void setBirthday(String birthday) {this.birthday = birthday;}

        public int getOrt() {return ort;}

        public void setOrt(int ort) {this.ort = ort;}

        @Override
        public String toString() {
            return "FormObject{" +
                "name='" + name + '\'' +
                ", vorname='" + vorname + '\'' +
                ", strasse='" + strasse + '\'' +
                ", birthday='" + birthday + '\'' +
                ", ort='" + ort + '\'' +
                '}';
        }
    }
}
