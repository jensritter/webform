package org.jens.webforms.example;

import org.jens.shorthand.io.HttpHelper;
import org.jens.shorthand.spring.test.annotation.ShorthandTestWeb;
import org.jens.shorthand.spring.test.web.SpringTestWebInitializer;
import org.jens.webforms.example.MyWebRunner.WebRunnerConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Jens Ritter on 15.01.2016.
 */
@ExtendWith(SpringExtension.class)
@ShorthandTestWeb(classes = WebRunnerConfig.class)
public class MyWebRunner extends SpringTestWebInitializer {

    @Configuration
    @Import(Application.class)
    public static class WebRunnerConfig {

        @Value("${test.user}")
        String testUser;

        @Value("${test.pass}")
        String testPass;

        @Bean
        public TestUserLogin testUserLogin() {
            return new TestUserLogin(testUser, testPass);
        }

        @Bean
        RestTemplate restTemplate() {
            return new RestTemplate();
        }
    }

    public static final class TestUserLogin {
        private final String username;
        private final String password;

        public TestUserLogin(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TestUserLogin userLogin;

    @Test
    public void testIndex() throws IOException {
        String content = HttpHelper.getContent(this.baseurl());
        assertNotNull(content);
        assertTrue(content.contains("bootstrap-theme")); // bootstrap-theme.min.css
    }

}
