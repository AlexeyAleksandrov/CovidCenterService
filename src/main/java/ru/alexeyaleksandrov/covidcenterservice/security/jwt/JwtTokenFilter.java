package ru.alexeyaleksandrov.covidcenterservice.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter
{
    private JwtUtils jwtUtils;
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        String jwt = null;
        String username = null;
        UserDetails userDetails = null;
        UsernamePasswordAuthenticationToken auth = null;

        try
        {
            String headerAuth = request.getHeader("Authorization");
            if(headerAuth != null && headerAuth.startsWith("Bearer "))
            {
                jwt = headerAuth.substring(7);
            }
            if(jwt != null)
            {
                try
                {
                    username = jwtUtils.getNameFromJwt(jwt);
                }
                catch (ExpiredJwtException e) {}
                if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
                {
                    userDetails = userDetailsService.loadUserByUsername(username);  // загружаем данные о пользователе из базы
                    auth = new UsernamePasswordAuthenticationToken(userDetails, null);     // авторизуем пользователя
                    SecurityContextHolder.getContext().setAuthentication(auth);     // пропихиваем аутентифицированного пользователя в контекст Spring Security
                }
            }
        }
        catch (Exception e)
        {

        }
        filterChain.doFilter(request, response);    // дёргаем следующий фильтр, если он есть в цепочке
    }
}
