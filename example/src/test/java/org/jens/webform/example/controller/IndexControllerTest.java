package org.jens.webform.example.controller;

import org.jens.webform.example.MyMockRunner;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Jens Ritter on 15.10.2016.
 */
class IndexControllerTest extends MyMockRunner {

    @Test
    void testIndex() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().is3xxRedirection());

    }


    @RestController
    static class TestRestController {
        @RequestMapping("/secured-junit")
        String secured() {return "secured";}
    }


    @Test
    @WithMockUser(authorities = "JUNIT")
    void testSecurredWithPermissions() throws Exception {
        mockMvc.perform(get("/secured-junit"))
            .andExpect(status().isOk())
            .andExpect(content().string("secured"));
    }

    @Test
    @WithMockUser(authorities = "JUNIT")
    void testPostWithPermissions() throws Exception {
        mockMvc.perform(
                get("/secured-junit").with(SecurityMockMvcRequestPostProcessors.csrf())
            )
            .andExpect(status().isOk())
            .andExpect(content().string("secured"));
    }

}
