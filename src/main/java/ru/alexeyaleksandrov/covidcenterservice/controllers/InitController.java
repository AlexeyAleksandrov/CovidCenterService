package ru.alexeyaleksandrov.covidcenterservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alexeyaleksandrov.covidcenterservice.models.services.MedicalService;
import ru.alexeyaleksandrov.covidcenterservice.models.users.Member;
import ru.alexeyaleksandrov.covidcenterservice.models.users.Permission;
import ru.alexeyaleksandrov.covidcenterservice.models.users.Role;
import ru.alexeyaleksandrov.covidcenterservice.repositories.services.MedicalServiceRepository;
import ru.alexeyaleksandrov.covidcenterservice.repositories.users.MemberRepository;
import ru.alexeyaleksandrov.covidcenterservice.repositories.users.PermissionRepository;
import ru.alexeyaleksandrov.covidcenterservice.repositories.users.RoleRepository;
import ru.alexeyaleksandrov.covidcenterservice.services.TimestampConverter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/init")
@AllArgsConstructor
public class InitController
{
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    MedicalServiceRepository medicalServiceRepository;
    MemberRepository memberRepository;
    PasswordEncoder passwordEncoder;

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
//        roles.add(new Role("No role", List.of(permission)));
//        roles.add(new Role("Assistant", List.of(permission)));
//        roles.add(new Role("AssistantResearcher", List.of(permission)));
//        roles.add(new Role("Booker", List.of(permission)));
        roles.add(new Role("Administrator", List.of(permission)));
        roleRepository.saveAllAndFlush(roles);
        return ResponseEntity.ok("Saved!");
    }

    @GetMapping("/users")
    public ResponseEntity<String> initUsers()
    {
        String csvFile = "C:\\Users\\ASUS\\Downloads\\COVID-центр\\COVID-центр\\Ресурсы 1\\users.csv";

        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile)))
        {
            br.readLine();  // пропускаем 1ю строку
            while ((line = br.readLine()) != null)
            {
//                System.out.println(line);
                int first = line.indexOf("[");  // начало массива
                int last = line.lastIndexOf("]")+1;
                String servicesJson = line.substring(first, last);
                line = line.replace(servicesJson, "");
                servicesJson = servicesJson.replace("\"", "");

                String[] data = line.split(",");

                Member member = new Member();
                member.setId(Long.parseLong(data[0]));  // id
                member.setFullName(data[1]);    // full_name
                member.setLogin(data[2]);   // login
                String hashed = passwordEncoder.encode(data[3]);
                member.setPassword(hashed);    // password
                member.setIp(data[4]);  // ip

                long timestamp = TimestampConverter.convertMMddyyyyToTimestamp(data[5]);
                member.setLastEnterTime(timestamp);     // last_enter

                List<Integer> codeList = new ArrayList<>();
                try
                {
                    //                    servicesJson = servicesJson.replace("\"", "");
                    JSONArray jsonArray = new JSONArray(servicesJson);
                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        int code = jsonArray.getJSONObject(i).getInt("code");
                        codeList.add(code);
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }

                // удаляем дубликаты
                Set<Integer> codeSet = Set.copyOf(codeList);

                List<MedicalService> allowServices = new ArrayList<>();
                for (int code : codeSet)
                {
                    MedicalService service = medicalServiceRepository.findById((long) code).orElseThrow();
                    allowServices.add(service);
                }
                member.setAllowServices(allowServices);

                Role role = roleRepository.findAll().get(Integer.parseInt(data[7]));
                member.setRole(role);

                memberRepository.save(member);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return ResponseEntity.ok("Saved!");
    }
}
