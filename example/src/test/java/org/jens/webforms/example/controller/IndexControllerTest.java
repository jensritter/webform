package org.jens.webforms.example.controller;

import org.jens.webforms.example.MyMockRunner;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Jens Ritter on 15.10.2016.
 */
public class IndexControllerTest extends MyMockRunner {

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().is3xxRedirection());

    }


    @RestController
    public static class TestRestController {
        @GetMapping("/restdocs-testing-only-remove-if-unwanted")
        public RestResponse when(@RequestParam(value = "name", required = false) String name) {
            return new RestResponse(LocalTime.now());
        }

        @RequestMapping("/secured-junit")
        public String secured() {return "secured";}
    }

    private static final class RestResponse {
        final int hour;
        final int minute;
        final int second;
        final int nano;

        RestResponse(LocalTime lt) {
            this.hour = lt.getHour();
            this.minute = lt.getMinute();
            this.second = lt.getSecond();
            this.nano = lt.getNano();
        }

        public int getHour() {
            return hour;
        }

        public int getMinute() {
            return minute;
        }

        public int getSecond() {
            return second;
        }

        public int getNano() {
            return nano;
        }
    }

    @Test
    public void testRestDocs() throws Exception {
        /*

            Für requestParameters,responseFields,pathParameters,... :

            import static org.springframework.restdocs.payload.PayloadDocumentation.*;
            import static org.springframework.restdocs.request.RequestDocumentation.*;

         */
        mockMvc.perform(get("/restdocs-testing-only-remove-if-unwanted"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andDo(MockMvcRestDocumentation.document("restdocs-test",
                requestParameters(
                    parameterWithName("name").description("Name").optional()
                )
                ,
                responseFields(
                    fieldWithPath("hour").description("aktuelle stunde"),
                    fieldWithPath("minute").description("aktuelle stunde"),
                    fieldWithPath("second").description("aktuelle stunde"),
                    fieldWithPath("nano").description("aktuelle stunde")
                )
            ));

    }


    @Test
    public void testSecurredNoAuth() throws Exception {
        mockMvc.perform(get("/secured-junit"))
            .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithMockUser
    public void testSecurredMissingPermissions() throws Exception {
        mockMvc.perform(get("/secured-junit"))
            .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(authorities = "JUNIT")
    public void testSecurredWithPermissions() throws Exception {
        mockMvc.perform(get("/secured-junit"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = "JUNIT")
    public void testPostWithPermissions() throws Exception {
        mockMvc.perform(
                get("/secured-junit").with(SecurityMockMvcRequestPostProcessors.csrf())
            )
            .andExpect(status().isOk());
    }

}
