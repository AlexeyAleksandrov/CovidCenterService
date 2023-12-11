package ru.alexeyaleksandrov.covidcenterservice.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.alexeyaleksandrov.covidcenterservice.models.users.User;
import ru.alexeyaleksandrov.covidcenterservice.repositories.users.UsersRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter
{
    private JwtUtils jwtUtils;
    private UserDetailsService userDetailsService;
//    private AuthenticationManager authenticationManager;
//    private UsersRepository usersRepository;

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
//    {
//
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
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
                catch (ExpiredJwtException e)
                {
//                    System.out.println("ExpiredJwtException !!!");
                }
                if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
                {
                    userDetails = userDetailsService.loadUserByUsername(username);  // загружаем данные о пользователе из базы

//                    System.out.println("Authorities: " + userDetails.getAuthorities());
//                    System.out.println("user " + userDetails.getUsername());
//                    System.out.println("pass " + userDetails.getPassword());

                    auth = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());     // авторизуем пользователя
//                    User user =
//                    auth = new UsernamePasswordAuthenticationToken()

                    SecurityContextHolder.getContext().setAuthentication(auth);     // пропихиваем аутентифицированного пользователя в контекст Spring Security
//                    System.out.println("Авторизация выполнена!");
                }
            }
        }
        catch (Exception e)
        {
//            System.out.println("JWT Exception!!");
//            e.printStackTrace();
        }
        filterChain.doFilter(request, response);    // дёргаем следующий фильтр, если он есть в цепочке
    }
}
