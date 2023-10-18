package ru.alexeyaleksandrov.covidcenterservice.repositories.users;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexeyaleksandrov.covidcenterservice.models.users.Permission;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Long>
{}