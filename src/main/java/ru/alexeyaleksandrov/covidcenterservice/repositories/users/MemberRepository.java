package ru.alexeyaleksandrov.covidcenterservice.repositories.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.alexeyaleksandrov.covidcenterservice.models.users.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>
{
    Optional<Member> findUserByLogin(String username);

    boolean existsByLogin(String login);
}