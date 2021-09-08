package org.jens.webform.example.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jens.shorthand.io.IOHelper;
import org.jens.webform.example.MyMockRunner;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Jens Ritter on 05/09/2021.
 */
class MainControllerTest extends MyMockRunner {

    @Test
    void index() throws Exception {
        mockMvc.perform(get("/main"))
            .andExpect(status().isOk())
            .andExpect(view().name("index"))
        ;
    }

    @Test
    void schemaIsCorrectAndUnchanged() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/form"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();

        String json = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode currentNodes = mapper.readTree(json);

        String required = IOHelper.getStringFromClasspath(this, "api.json", StandardCharsets.UTF_8);
        JsonNode requiredNodes = mapper.readTree(required);

        assertThat(
            mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requiredNodes)
        ).isEqualTo(
            mapper.writerWithDefaultPrettyPrinter().writeValueAsString(currentNodes)
        );

    }

}
