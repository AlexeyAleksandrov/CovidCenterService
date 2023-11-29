package ru.alexeyaleksandrov.covidcenterservice.security.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import ru.alexeyaleksandrov.covidcenterservice.security.jwt.JwtTokenFilter;
import ru.alexeyaleksandrov.covidcenterservice.security.services.CustomAccessDeniedHandler;
import ru.alexeyaleksandrov.covidcenterservice.security.services.CustomAuthenticationEntryPoint;
import ru.alexeyaleksandrov.covidcenterservice.security.services.UserService;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration
{
    private UserService userService;
    private JwtTokenFilter jwtTokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    // нужен для доступа к процессу аутентификации и попытаться аутентифицировать пользователя
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
    {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // сборка настроек Spring Security
    @Bean
    @Primary
    public AuthenticationManagerBuilder configureAuthenticationManagerBuilder(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception
    {
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder;
    }

    // 25:14
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http
                .csrf(AbstractHttpConfigurer::disable)      // отключаем csrf
                .cors(httpSecurityCorsConfigurer ->
                        httpSecurityCorsConfigurer.configurationSource(request ->
                                new CorsConfiguration().applyPermitDefaultValues())
                )   // отключаем cors (даем доступ всем приложениям напрямую)
                .exceptionHandling(exceptions -> {
                    exceptions.accessDeniedHandler(new CustomAccessDeniedHandler());
                    exceptions.authenticationEntryPoint(new CustomAuthenticationEntryPoint());
//                    exceptions.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
                }
                )   // при попытке подключиться туда, где нужна авторизация, будем выдавать 401 ошибку
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )   // выключаем механизм сессий, т.к. мы используем JWT токен, вместо ID и cookie
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/secured/user").fullyAuthenticated()
                        .requestMatchers("/api/v1/orders/**").fullyAuthenticated()
                        .anyRequest().authenticated()
//                        .anyRequest().permitAll()
//                         .anyRequest().rememberMe()
//                        .anyRequest().permitAll()
                )   // позволяем всем пройти на страницу авторизации, но требуем авторизации для защищённой области, все остальные запросы разрешаем
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);     // добавляем фильтр для авторизации

        return http.build();
    }
}
