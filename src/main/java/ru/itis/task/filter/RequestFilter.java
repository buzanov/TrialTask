package ru.itis.task.filter;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@ApplicationScope
public class RequestFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (SecurityContextHolder.getContext().getAuthentication() == null
                && !((HttpServletRequest) servletRequest).getRequestURI().contains("sign")) {
            System.out.println(((HttpServletRequest) servletRequest).getRequestURI());
            ((HttpServletResponse) servletResponse).sendRedirect("/signIn");

        } else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

}
