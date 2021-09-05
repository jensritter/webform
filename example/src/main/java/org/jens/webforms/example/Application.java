package org.jens.webforms.example;

import org.jens.shorthand.spring.boot.HostnameAwareSpringApplicationBuilder;
import org.jens.webforms.example.config.AppConfig;
import org.jens.webforms.example.config.MvcConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

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

}
