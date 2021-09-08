package org.jens.webform.example.config;

import org.jens.shorthand.security.config.ShorthandSecurityDefaults;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Jens Ritter on 16/03/2021.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        ShorthandSecurityDefaults.configure(http)
            .csrf().disable()
            .authorizeRequests()
//                .antMatchers("/secured-junit").hasAuthority("JUNIT")
//                .antMatchers("/main").authenticated()
                .anyRequest().denyAll();
        //@formatter:on
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        ShorthandSecurityDefaults.configure(web)
            .ignoring()
            .antMatchers("/**")
//            .antMatchers("/restdocs-testing-only-remove-if-unwanted")
//            .antMatchers("/template-edit")
            //.anyRequest(); uncomment to disable Secrutiy, but keep dependencies
            .and()
        ;
    }
}
