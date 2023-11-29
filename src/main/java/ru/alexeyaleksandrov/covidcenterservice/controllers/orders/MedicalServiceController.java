package ru.alexeyaleksandrov.covidcenterservice.controllers.orders;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alexeyaleksandrov.covidcenterservice.models.services.MedicalService;
import ru.alexeyaleksandrov.covidcenterservice.repositories.services.MedicalServiceRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medical_services")
@AllArgsConstructor
public class MedicalServiceController
{
    MedicalServiceRepository medicalServiceRepository;

    @GetMapping("/")
    public ResponseEntity<List<MedicalService>> getAll()
    {
        List<MedicalService> services = medicalServiceRepository.findAll();     // получаем список сервисов
        if(services.isEmpty())      // если загрузить не удалось
        {
            return ResponseEntity.notFound().build();   // возвращаем ошибку
        }

        return ResponseEntity.ok(services);     // возвращаем список
    }
}
