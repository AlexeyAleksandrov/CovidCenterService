package ru.alexeyaleksandrov.covidcenterservice.security.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.alexeyaleksandrov.covidcenterservice.models.users.Member;
import ru.alexeyaleksandrov.covidcenterservice.repositories.users.MemberRepository;
import ru.alexeyaleksandrov.covidcenterservice.security.models.SecurityUserDetails;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService
{
    private MemberRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException
    {
        Member user = userRepository.findUserByLogin(login).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found", login)
        ));

        return SecurityUserDetails.build(user);
    }
}
