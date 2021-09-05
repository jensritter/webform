package org.jens.webforms.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.constraints.NotEmpty;

/**
 * @author Jens Ritter on 23/08/2021.
 */
@Controller
public class EditController {

    @GetMapping("/template-edit")
    public String index(Model model) {
        if(!model.containsAttribute("editFormBean")) {
            model.addAttribute("editFormBean", new EditFormBean());
        }
        return "index";
    }

    @PostMapping("/template-edit")
    public String handlePost(EditFormBean editFormBean, BindingResult bindingResult, Model model) {
        if(formDataContainsErrors(editFormBean, bindingResult)) {
            return "/index"; //has errors
        }
        model.addAttribute("success", "success");
        return "index";
    }

    private boolean formDataContainsErrors(EditFormBean editFormBean, BindingResult bindingResult) {
        if("test".equals(editFormBean.getName())) {
            bindingResult.addError(new FieldError("editFormBean", "name", "name darf nicht test enthalten"));
        }

        return bindingResult.hasErrors();
    }


    public static class EditFormBean {
        @NotEmpty
        private String name;

        public String getName() {return name;}

        public void setName(String name) {this.name = name;}
    }
}
