package ru.alexeyaleksandrov.covidcenterservice.repositories.users;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexeyaleksandrov.covidcenterservice.models.users.LoginHistory;

public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long>
{}