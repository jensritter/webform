package org.jens.webforms.example;

import org.jens.shorthand.spring.test.annotation.ShorthandTestSpring;
import org.jens.webforms.example.MySpringRunner.SpringRunnerConfig;
import org.jens.webforms.example.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Jens Ritter on 17.12.2015.
 */
@ExtendWith(SpringExtension.class)
@ShorthandTestSpring(classes = SpringRunnerConfig.class)
public class MySpringRunner {

    @Configuration
    @EnableAutoConfiguration(exclude = JmxAutoConfiguration.class)
    @Import(AppConfig.class)
    public static class SpringRunnerConfig {

    }

    @Autowired
    private GenericApplicationContext ctx;

    @Test
    public void testDI() {
        assertNotNull(ctx);
    }

}
