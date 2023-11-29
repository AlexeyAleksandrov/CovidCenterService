package ru.alexeyaleksandrov.covidcenterservice.security.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler
{
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException
    {
        // Обработка ошибки доступа
        System.out.println("Ошибка авторизации:");
        accessDeniedException.printStackTrace();
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
    }
}