package org.jens.testing.forms;

import org.jens.shorthand.spring.test.annotation.ShorthandTestSpring;
import org.jens.testing.forms.MySpringRunner.SpringRunnerConfig;
import org.jens.testing.forms.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
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

        @Bean(name = "jdbc_test")
        public EmbeddedDatabaseBuilder builder() {
            return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .generateUniqueName(true)
                .ignoreFailedDrops(true)
                .addScript("testdata.sql");
        }

        /*
        @Bean(name = "jdbc_test")
        public DataSource jdbcTest() {
            return JdbcNG.h2().getDs();
        }
        */
    }

    @Autowired
    private GenericApplicationContext ctx;

    @Test
    public void testDI() {
        assertNotNull(ctx);
    }

}
