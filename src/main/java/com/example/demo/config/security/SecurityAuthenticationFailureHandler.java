package com.example.demo.config.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class SecurityAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final String defaultFailureUrl;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws ServletException, IOException {
        try {
            System.out.println(defaultFailureUrl);
            System.out.println("err:"+exception.getMessage());
            request.getRequestDispatcher(this.defaultFailureUrl).forward(request, response);
            System.out.println(response.getStatus());
        } catch (Exception e) {
            System.out.println("eeeeeeeeeeeeee");
        }
    }
}
