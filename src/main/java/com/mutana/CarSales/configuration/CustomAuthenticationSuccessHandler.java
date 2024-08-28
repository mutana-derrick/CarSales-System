package com.mutana.CarSales.configuration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        String redirectUrl = "/";

        switch (role) {
            case "ROLE_ADMIN":
                redirectUrl = "/ceo/dashboard";
                break;
            case "ROLE_EDITOR":
                redirectUrl = "/manager/dashboard";
                break;
            case "ROLE_VIEWER":
                redirectUrl = "/salesperson/dashboard";
                break;
        }

        response.sendRedirect(redirectUrl);
    }
}

