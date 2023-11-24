package ru.alexeyaleksandrov.covidcenterservice.repositories.users;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexeyaleksandrov.covidcenterservice.models.users.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long>
{
    Optional<User> findUserByLogin(String username);

    boolean existsByLogin(String login);
}