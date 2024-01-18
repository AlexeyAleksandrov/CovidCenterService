package ru.alexeyaleksandrov.covidcenterservice.dto.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс запроса на задание/получение роли пользователю
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequest {
    private Long userId;    // id пользователя
    private Long roleId;    // id роли
}
