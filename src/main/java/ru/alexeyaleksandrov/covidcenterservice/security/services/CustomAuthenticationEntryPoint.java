package ru.alexeyaleksandrov.covidcenterservice.security.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint
{
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException
    {
        // Обработка ошибки аутентификации
//        System.out.println("Ошибка аутентификации:");
//        authException.printStackTrace();
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
    }
}
