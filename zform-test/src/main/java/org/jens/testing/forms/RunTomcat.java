package org.jens.testing.forms;

import org.jens.testing.forms.config.AppConfig;
import org.jens.testing.forms.config.MvcConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.sql.DataSource;

/**
 * Inside-Tomcat .war-Starter
 *
 * @author Jens Ritter on 02.03.2018.
 */
@EnableAutoConfiguration
@Configuration
@Import({
    MvcConfig.class,
    AppConfig.class,
})
public class RunTomcat extends SpringBootServletInitializer {

    @Configuration
    static class DataSources {

        @Primary
        @Bean(name = "jdbc_test")
        public DataSource testDatasourceConfig() {
            return new JndiDataSourceLookup().getDataSource("jdbc/test");
        }
    }

}
