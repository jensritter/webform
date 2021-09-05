package org.jens.webforms.example;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.jens.shorthand.spring.test.annotation.ShorthandTestWeb;
import org.jens.webforms.example.ApplicationTest.ApplicationTestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Jens Ritter on 26.01.2016.
 */
@ExtendWith(SpringExtension.class)
@ShorthandTestWeb(classes = ApplicationTestConfig.class)
public class ApplicationTest {

    @Configuration
    @Import(Application.class)
    public static class ApplicationTestConfig {


    }

    @Autowired
    private Application application;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;


    @LocalServerPort
    int port;

    @Test
    public void testApplicationStartup() throws IOException {
        HttpTestHelper helper = new HttpTestHelper(port);
        helper.login("admin", "admin", "/");

        try(CloseableHttpResponse response = helper.get("/")) {
            helper.assertRedirect(response, "/main");
        }
        try(CloseableHttpResponse response = helper.get("/main")) {
            helper.is2xxOk(response);
        }

        helper.logout();

        assertTrue(true);
    }

    private final Logger logger = LoggerFactory.getLogger(ApplicationTest.class);
}
