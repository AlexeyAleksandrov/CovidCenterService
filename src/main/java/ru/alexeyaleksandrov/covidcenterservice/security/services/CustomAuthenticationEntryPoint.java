package ru.alexeyaleksandrov.covidcenterservice.security.services;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint
{
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // Обработка ошибки аутентификации
//        System.out.println("Ошибка аутентификации:");
//        authException.printStackTrace();
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
    }
}
