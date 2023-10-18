package ru.alexeyaleksandrov.covidcenterservice.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.alexeyaleksandrov.covidcenterservice.security.models.SecurityUserDetails;

import java.util.Date;

@Component
public class JwtUtils
{
    @Value("${app.secret}")
    private String secret;

    @Value("${app.expirationMs}")
    private int lifeTime;

    public String generateToken(Authentication authentication)
    {
        SecurityUserDetails userDetails = (SecurityUserDetails) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + lifeTime))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    /** Достаёт из JWT токена имя пользователя
     * @param token полученный JWT токен, из которого надо достать имя
     * @return имя пользователя
     */
    public String getNameFromJwt(String token)
    {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
}
