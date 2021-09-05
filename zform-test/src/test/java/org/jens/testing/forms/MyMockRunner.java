package org.jens.testing.forms;

import org.jens.shorthand.spring.test.annotation.ShorthandTestWebMock;
import org.jens.testing.forms.MySpringRunner.SpringRunnerConfig;
import org.jens.testing.forms.config.MvcConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Jens Ritter on 15.01.2016.
 */
@ExtendWith(SpringExtension.class)
@AutoConfigureRestDocs(outputDir = "target/generated-restdocs")
@ShorthandTestWebMock(classes = MyMockRunner.MockRunnerConfig.class)
public class MyMockRunner {

    @Configuration
    @EnableAutoConfiguration(exclude = JmxAutoConfiguration.class)
    @Import(MvcConfig.class)
    public static class MockRunnerConfig extends SpringRunnerConfig {

    }

    @Autowired
    protected MockMvc mockMvc;

    @Test
    public void testDI() {
        assertNotNull(mockMvc);
    }

    @Test
    public void testGetIndex() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().is3xxRedirection());
    }
}
