package ru.alexeyaleksandrov.covidcenterservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alexeyaleksandrov.covidcenterservice.models.users.Permission;
import ru.alexeyaleksandrov.covidcenterservice.models.users.Role;
import ru.alexeyaleksandrov.covidcenterservice.repositories.users.PermissionRepository;
import ru.alexeyaleksandrov.covidcenterservice.repositories.users.RoleRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/init")
@AllArgsConstructor
public class InitController
{
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;

    @GetMapping("/permissions")
    public ResponseEntity<String> initPermissions()
    {
        List<Permission> permissions = new ArrayList<>();
        permissions.add(new Permission("No permission"));
        permissionRepository.saveAllAndFlush(permissions);
        return ResponseEntity.ok("Saved!");
    }

    @GetMapping("/roles")
    public ResponseEntity<String> initRoles()
    {
        Permission permission = permissionRepository.findById(1L).orElseThrow();

        List<Role> roles = new ArrayList<>();
        roles.add(new Role("No role", List.of(permission)));
        roleRepository.saveAllAndFlush(roles);
        return ResponseEntity.ok("Saved!");
    }
}
