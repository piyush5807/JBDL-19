package com.example.jbdl.demosecurity;

import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class SessionTimeFilter extends SecurityContextPersistenceFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        int expiry = httpServletRequest.getSession().getMaxInactiveInterval();

        System.out.println("expiry = " + expiry);

        httpServletRequest.getSession().setMaxInactiveInterval(expiry + 1800);

        System.out.println("expiry = " + httpServletRequest.getSession().getMaxInactiveInterval());

        chain.doFilter(request, response);
    }
}
