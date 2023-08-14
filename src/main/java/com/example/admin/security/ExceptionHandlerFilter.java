package com.example.admin.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (TokenValidException ex) {
            setErrorResponse(HttpStatus.UNAUTHORIZED, request, response, ex);
        }
    }

    public void setErrorResponse(HttpStatus status, HttpServletRequest request, HttpServletResponse response, Exception ex) throws IOException {

        response.setStatus(status.value());
        response.setContentType("application/json; charset=UTF-8");

        response.getWriter().write(
                ErrorResponse.of(
                                HttpStatus.UNAUTHORIZED.value(),
                                ex.getMessage(),
                                request
                        )
                        .convertToJson()
        );
    }
}
