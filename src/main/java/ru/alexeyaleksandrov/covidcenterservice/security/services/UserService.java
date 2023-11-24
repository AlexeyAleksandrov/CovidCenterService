package ru.alexeyaleksandrov.covidcenterservice.security.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.alexeyaleksandrov.covidcenterservice.models.users.User;
import ru.alexeyaleksandrov.covidcenterservice.repositories.users.UsersRepository;
import ru.alexeyaleksandrov.covidcenterservice.security.models.SecurityUserDetails;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService
{
    private UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException
    {
        User user = userRepository.findUserByLogin(login).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found", login)
        ));

        return SecurityUserDetails.build(user);
    }
}
