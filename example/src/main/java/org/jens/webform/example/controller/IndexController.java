package org.jens.webform.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * IndexPage-Controller
 *
 * @author Jens Ritter on 17.12.2015.
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "redirect:/main";
    }

}
