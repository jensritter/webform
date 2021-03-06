package org.jens.webform.example.controller;


import org.jens.webforms.FBoolean;
import org.jens.webforms.FComboBox;
import org.jens.webforms.FDate;
import org.jens.webforms.FString;
import org.jens.webforms.WebForm;
import org.jens.webforms.WebFormBuilder;
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
    public WebForm schema() {
        List<String> orte = Arrays.asList(
            "Hannover", "Goslar", "Bad Tölz", "Hamburg"
        );

        WebFormBuilder builder = new WebFormBuilder()
            .titleSubmit("Submit");

        builder.add("name", new FString("Name").description("description-2").required(true));
        builder.add("vorname", new FString("Vorname").description("description-3"));
        builder.add("strasse", new FString("Straße").description("description-4"));
        builder.add("birthday", new FDate("Geburtstag").description("description-5"));
        builder.add("heute", new FBoolean("Ist es heute", "ja/nein").description("description-6"));
        builder.add("ort", new FComboBox("Orte", orte).description("description-1"));

        return builder.toWebForm();
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
