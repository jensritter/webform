package org.jens.testing.forms;

import org.jens.shorthand.jdbc.ng.JdbcNG;
import org.jens.shorthand.spring.boot.HostnameAwareSpringApplicationBuilder;
import org.jens.testing.forms.config.AppConfig;
import org.jens.testing.forms.config.MvcConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Standalone ApplicationStarter
 *
 * @author Jens Ritter on 02.03.2018.
 */
@EnableAutoConfiguration
@Configuration
@Import({
    MvcConfig.class,
    AppConfig.class,
})
public class Application {

    /**
     * Starter
     *
     * @param args cli-args
     **/
    public static void main(String[] args) {
        new HostnameAwareSpringApplicationBuilder(Application.class)
            .run(args);
    }

    /**
     * Test-Datasource
     */
    @Configuration
    public static class DataSources {
        @Primary
        @Bean(name = "jdbc_test")
        public DataSource testDatasourceConfig() {
            return JdbcNG.h2().getDataSource();
        }
    }


}
