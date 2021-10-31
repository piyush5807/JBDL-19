package com.example.jbdl.libraryapp;

import com.example.jbdl.libraryapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class LibraryConfig extends WebSecurityConfigurerAdapter {

    // USER_ROLE
    // ADMIN_ROLE (LIBRARIAN)

    @Autowired
    UserService userService;

    @Value("${app.security.admin_role}")
    private String ADMIN_ROLE;

    @Value("${app.security.student_role}")
    private String STUDENT_ROLE;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/book/**").hasAnyAuthority(ADMIN_ROLE, STUDENT_ROLE)
                .antMatchers("/book/**").hasAuthority(ADMIN_ROLE)
                .antMatchers(HttpMethod.POST, "/student/**").permitAll()
                .antMatchers(HttpMethod.GET, "/student/id/**").hasAuthority(ADMIN_ROLE)
                .antMatchers("/student/**").hasAuthority(STUDENT_ROLE)
                .antMatchers(HttpMethod.GET, "/transaction/fine/student_id/**").hasAuthority(ADMIN_ROLE)
                .antMatchers("/transaction/**").hasAuthority(STUDENT_ROLE)
                .antMatchers("/admin/**").hasAuthority(ADMIN_ROLE)
                .antMatchers("/get_student/**").hasAnyAuthority(STUDENT_ROLE, ADMIN_ROLE)
                .and()
                .formLogin();
    }

    @Bean
    public PasswordEncoder getPE(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RedisTemplate<String, Object> getTemplate(){

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(
                "redis-12476.c89.us-east-1-3.ec2.cloud.redislabs.com", 12476
        );

        redisStandaloneConfiguration.setPassword("EDYLWiqGGLSBzi8vHAGANqWhX18QcJp5");
        LettuceConnectionFactory lettuceConnectionFactory =
                new LettuceConnectionFactory(redisStandaloneConfiguration);

        lettuceConnectionFactory.afterPropertiesSet();

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);

        return redisTemplate;

    }
}
