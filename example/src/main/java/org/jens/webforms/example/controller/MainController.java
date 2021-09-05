package org.jens.webforms.example.controller;


import org.jens.webforms.core.FBoolean;
import org.jens.webforms.core.FComboBox;
import org.jens.webforms.core.FDate;
import org.jens.webforms.core.FString;
import org.jens.webforms.core.JsonForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

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
    public JsonForm schema() {
        List<String> orte = Arrays.asList(
            "Hannover", "Goslar", "Bad Tölz", "Hamburg"
        );


        JsonForm response = new JsonForm();
        response.setTitleSubmit("Submit");
        response.add("name", new FString("Name").setDescription("description-2").setRequired(true));
        response.add("vorname", new FString("Vorname").setDescription("description-3"));
        response.add("strasse", new FString("Straße").setDescription("description-4"));
        response.add("birthday", new FDate("Geburtstag").setDescription("description-5"));
        response.add("heute", new FBoolean("Ist es heute", "ja/nein").setDescription("description-6"));
        response.add("ort", new FComboBox("Orte", orte).setDescription("description-1"));

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
