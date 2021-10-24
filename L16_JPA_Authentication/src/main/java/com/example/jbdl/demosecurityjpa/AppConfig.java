package com.example.jbdl.demosecurityjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig extends WebSecurityConfigurerAdapter {

    private static final String ADMIN_AUTHORITY = "ADMIN";
    private static final String STUDENT_AUTHORITY = "STUDENT";
    private static final String STUDENT_CREATE_AUTHORITY = "STUDENT_CREATE";


    @Autowired
    AppService appService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasAuthority(ADMIN_AUTHORITY)
                .antMatchers(HttpMethod.POST, "/student/**").hasAuthority(STUDENT_CREATE_AUTHORITY)
                .antMatchers("/student/**").hasAuthority(STUDENT_AUTHORITY)
                .antMatchers("/**").permitAll()
                .and()
                .formLogin();
    }

    @Bean
    public PasswordEncoder getPE(){
        return new BCryptPasswordEncoder();
    }

}

