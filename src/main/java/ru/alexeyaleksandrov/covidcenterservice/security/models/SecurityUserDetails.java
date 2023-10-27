package ru.alexeyaleksandrov.covidcenterservice.security.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.alexeyaleksandrov.covidcenterservice.models.users.Member;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class SecurityUserDetails implements UserDetails
{
    private Long id;
    private String userName;
    private String password;

    public static SecurityUserDetails build (Member user)
    {
        return new SecurityUserDetails(
                user.getId(),
                user.getLogin(),
                user.getPassword()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return null;
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public String getUsername()
    {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}
