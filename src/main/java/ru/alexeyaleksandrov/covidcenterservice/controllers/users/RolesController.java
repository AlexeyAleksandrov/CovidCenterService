package ru.alexeyaleksandrov.covidcenterservice.controllers.users;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alexeyaleksandrov.covidcenterservice.dto.users.RoleRequest;
import ru.alexeyaleksandrov.covidcenterservice.models.users.Role;
import ru.alexeyaleksandrov.covidcenterservice.models.users.User;
import ru.alexeyaleksandrov.covidcenterservice.repositories.users.RoleRepository;
import ru.alexeyaleksandrov.covidcenterservice.repositories.users.UsersRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/roles")
@AllArgsConstructor
public class RolesController {
    private RoleRepository roleRepository;
    private UsersRepository usersRepository;

    /**
     * @return список всех доступных ролей
     */
    @GetMapping("/")
    private List<Role> getAll() {
        return roleRepository.findAll().stream()
                .sorted(new Comparator<Role>() {
                    @Override
                    public int compare(Role o1, Role o2) {
                        return Long.compare(o1.getId(), o2.getId());
                    }
                })
                .collect(Collectors.toList());
    }

    /**
     * @param userId id пользователя
     * @return роль пользователя в системе
     */
    @GetMapping("/user/{userId}")
    private Role getRoleByUser(@PathVariable Long userId) {
        return usersRepository
                .findById(userId)
                .orElseThrow()
                .getRole();
    }

    /**
     * @param roleRequest параметры запроса, id пользователя и роли
     * @return новая роль пользователя
     */
    @PostMapping("/user")
    private ResponseEntity<RoleRequest> setUserRole(@RequestBody RoleRequest roleRequest)
    {
        if(!roleRepository.existsById(roleRequest.getRoleId()))
        {
            return ResponseEntity.badRequest().build();     // если id роли некорректный
        }
        else if (!usersRepository.existsById(roleRequest.getUserId()))  {
            return ResponseEntity.badRequest().build();     // если id пользователя некорректный
        }

        Role role = roleRepository.getById(roleRequest.getRoleId());    // получаем роль
        User user = usersRepository.getById(roleRequest.getUserId());   // получаем пользователя
        user.setRole(role);     // задаём новую роль
        usersRepository.save(user);     // обновляем данные в БД

        RoleRequest roleRequestAnswer = new RoleRequest(user.getId(), user.getRole().getId());  // ответ
        return ResponseEntity.ok(roleRequestAnswer);
    }
}
