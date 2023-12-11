package ru.alexeyaleksandrov.covidcenterservice.security.services;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler
{
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // Обработка ошибки доступа
//        System.out.println("Ошибка авторизации:");
//        accessDeniedException.printStackTrace();
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
    }
//    @Override
//    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException
//    {
        // Обработка ошибки доступа
//        System.out.println("Ошибка авторизации:");
//        accessDeniedException.printStackTrace();
//        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
//    }
}