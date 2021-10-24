package com.example.jbdl.demosecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig extends WebSecurityConfigurerAdapter {

    private static final String AUTHORITY_ADMIN = "ADMIN";
    private static final String AUTHORITY_STUDENT = "STUDENT";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Jim")
                .password("Jim123")
                .authorities("STUDENT")
                .and()
                .withUser("John")
                .password("John123")
                .authorities(AUTHORITY_ADMIN);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasAuthority(AUTHORITY_ADMIN)
                .antMatchers(HttpMethod.POST, "/student/**").hasAuthority(AUTHORITY_ADMIN)
                .antMatchers(HttpMethod.DELETE, "/student/**").hasAuthority(AUTHORITY_ADMIN)
                .antMatchers("/student/**").hasAnyAuthority(AUTHORITY_STUDENT, AUTHORITY_ADMIN)
                .antMatchers("/**").permitAll()
                .and()
                .formLogin();
    }

    @Bean
    public PasswordEncoder getPE(){
        return NoOpPasswordEncoder.getInstance();
    }
}
