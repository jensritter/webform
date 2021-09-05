package org.jens.webforms.example;

import org.jens.webforms.example.config.AppConfig;
import org.jens.webforms.example.config.MvcConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

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


}
