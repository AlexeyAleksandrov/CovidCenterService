package ru.alexeyaleksandrov.covidcenterservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alexeyaleksandrov.covidcenterservice.dto.SignInRequest;
import ru.alexeyaleksandrov.covidcenterservice.dto.SignUpRequest;
import ru.alexeyaleksandrov.covidcenterservice.models.users.LoginHistory;
import ru.alexeyaleksandrov.covidcenterservice.models.users.Member;
import ru.alexeyaleksandrov.covidcenterservice.models.users.Role;
import ru.alexeyaleksandrov.covidcenterservice.repositories.users.LoginHistoryRepository;
import ru.alexeyaleksandrov.covidcenterservice.repositories.users.MemberRepository;
import ru.alexeyaleksandrov.covidcenterservice.repositories.users.RoleRepository;
import ru.alexeyaleksandrov.covidcenterservice.security.jwt.JwtUtils;
import ru.alexeyaleksandrov.covidcenterservice.services.TimestampConverter;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class SecurityController
{
    private MemberRepository memberRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;
    private RoleRepository roleRepository;
    private LoginHistoryRepository loginHistoryRepository;

    @PostMapping("/signup")
    ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest)  // регистрация
    {
        if(memberRepository.existsByLogin(signUpRequest.getUsername()))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Choose different name!");
        }

        String hashed = passwordEncoder.encode(signUpRequest.getPassword());

        Member member = new Member();
        member.setLogin(signUpRequest.getUsername());
        member.setFullName(signUpRequest.getFullName());
        member.setPassword(hashed);

        Role role = roleRepository.findAll().get(0);
        member.setRole(role);

        memberRepository.save(member);
        return ResponseEntity.ok("Success!");
    }

    @PostMapping("/signin")
    ResponseEntity<?> signin(@RequestBody SignInRequest signInRequest)
    {
        // сохраняем информацию о входе
        LoginHistory loginHistory = new LoginHistory();
        loginHistory.setLogin(signInRequest.getUsername());     // сохраняем логин
        loginHistory.setEnterDate(TimestampConverter.getLocalTimestamp());      // сохраняем время входа

        Authentication authentication = null;
        try     // пытаемся авторизовать пользователя
        {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
        }
        catch (BadCredentialsException e)
        {
            // сохраняем историю
            loginHistory.setSuccessful(false);
            loginHistoryRepository.save(loginHistory);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // если авторизация корректна
        SecurityContextHolder.getContext().setAuthentication(authentication);   // пропихиваем авторизацию в контекст
        String jwt = jwtUtils.generateToken(authentication);    // генерируем токен

        // сохраняем историю
        loginHistory.setSuccessful(true);
        loginHistoryRepository.save(loginHistory);

        return ResponseEntity.ok(jwt);      // возвращаем токен в теле ответа
    }
}
