package ru.alexeyaleksandrov.covidcenterservice.imports;

import lombok.AllArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alexeyaleksandrov.covidcenterservice.imports.BloodServiceDataset;
import ru.alexeyaleksandrov.covidcenterservice.imports.BloodServicesRecord;
import ru.alexeyaleksandrov.covidcenterservice.imports.PatientDataset;
import ru.alexeyaleksandrov.covidcenterservice.imports.PatientRecord;
import ru.alexeyaleksandrov.covidcenterservice.models.insurance.InsurancePolicyCompany;
import ru.alexeyaleksandrov.covidcenterservice.models.insurance.SocialType;
import ru.alexeyaleksandrov.covidcenterservice.models.services.Analyzer;
import ru.alexeyaleksandrov.covidcenterservice.models.services.AnalyzerResult;
import ru.alexeyaleksandrov.covidcenterservice.models.services.AnalyzerResultStatus;
import ru.alexeyaleksandrov.covidcenterservice.models.services.MedicalService;
import ru.alexeyaleksandrov.covidcenterservice.models.users.Member;
import ru.alexeyaleksandrov.covidcenterservice.models.users.Patient;
import ru.alexeyaleksandrov.covidcenterservice.models.users.Permission;
import ru.alexeyaleksandrov.covidcenterservice.models.users.Role;
import ru.alexeyaleksandrov.covidcenterservice.repositories.insurance.InsurancePolicyCompanyRepository;
import ru.alexeyaleksandrov.covidcenterservice.repositories.services.AnalyzerRepository;
import ru.alexeyaleksandrov.covidcenterservice.repositories.services.AnalyzerResultRepository;
import ru.alexeyaleksandrov.covidcenterservice.repositories.services.MedicalServiceRepository;
import ru.alexeyaleksandrov.covidcenterservice.repositories.users.MemberRepository;
import ru.alexeyaleksandrov.covidcenterservice.repositories.users.PatientRepository;
import ru.alexeyaleksandrov.covidcenterservice.repositories.users.PermissionRepository;
import ru.alexeyaleksandrov.covidcenterservice.repositories.users.RoleRepository;
import ru.alexeyaleksandrov.covidcenterservice.services.TimestampConverter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
    PatientRepository patientRepository;
    InsurancePolicyCompanyRepository insurancePolicyCompanyRepository;
    AnalyzerResultRepository analyzerResultRepository;
    AnalyzerRepository analyzerRepository;

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

    @GetMapping("/patients")
    public ResponseEntity<String> patients()
    {
        try
        {
            // Создание объекта JAXBContext
            JAXBContext jaxbContext = JAXBContext.newInstance(PatientDataset.class);

            // Создание объекта Unmarshaller
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            // Загрузка XML файла
            File file = new File("C:\\Users\\ASUS\\Downloads\\COVID-центр\\COVID-центр\\Ресурсы 1\\patients.xml");

            // Десериализация XML в объект Dataset
            PatientDataset patientDataset = (PatientDataset) jaxbUnmarshaller.unmarshal(file);

            // Получение списка записей пациентов
            List<PatientRecord> patientRecords = patientDataset.getRecordList();

            // Создание списка пациентов для сохранения данных
            List<Patient> patients = new ArrayList<>();

            // страховые компании
            List<InsurancePolicyCompany> insurancePolicyCompanies = new ArrayList<>();
            insurancePolicyCompanies = insurancePolicyCompanyRepository.findAll();

            // Преобразование записей пациентов в объекты Patient
            for (PatientRecord patientRecord : patientRecords)
            {
                Patient patient = new Patient();
                patient.setId(patientRecord.getId());
                patient.setFullName(patientRecord.getFullName());
                // Установка остальных полей пациента
                patient.setPassport(patientRecord.getPassportS() + patientRecord.getPassportN());
                patient.setPhoneNumber(patientRecord.getPhone());
                patient.setEmail(patientRecord.getEmail());
                patient.setLogin(patientRecord.getLogin());
                String hashed = passwordEncoder.encode(patientRecord.getPassword());
                patient.setPassword(hashed);
                patient.setBirthday(patientRecord.getBirthdateTimestamp());
                patient.setSocialSecNumber(patientRecord.getSocialSecNumber());
                patient.setSocialType(SocialType.valueOf(patientRecord.getSocialType().toUpperCase()));
                patient.setCountry(patientRecord.getCountry());
                patient.setIpAddress(patientRecord.getIpAddress());
                patient.setUserAgent(patientRecord.getUserAgent());

                InsurancePolicyCompany insurancePolicy = insurancePolicyCompanies.stream().filter(insurancePolicyCompany -> insurancePolicyCompany.getName().equals(patientRecord.getInsuranceName())).findFirst().orElse(null);
                if(insurancePolicy == null)
                {
                    // Создание объекта InsurancePolicyCompany и установка его для пациента
                    insurancePolicy = new InsurancePolicyCompany();
                    insurancePolicy.setName(patientRecord.getInsuranceName());
                    insurancePolicy.setAddress(patientRecord.getInsuranceAddress());
                    insurancePolicy.setIndividualTaxNumber(patientRecord.getInsuranceInn());
                    insurancePolicy.setPolicyCode(patientRecord.getInsurancePc());
                    insurancePolicy.setBankIdentificationCode(patientRecord.getInsuranceBik());

                    insurancePolicyCompanies.add(insurancePolicy);
                }
                patient.setInsurancePolicy(insurancePolicy);

                patients.add(patient);
            }

            // Вывод данных пациентов
//            for (Patient patient : patients)
//            {
//                System.out.println(patient);
//            }

            // Вывод данных компаний
//            for (InsurancePolicyCompany company : insurancePolicyCompanies)
//            {
//                System.out.println(company);
//            }

            int maxEmail = patients.stream()
                    .mapToInt(p -> p.getEmail().length())
                    .max().orElse(-1);
            System.out.println("Max Email: " + maxEmail);
            Patient patient = patients.stream()
                    .filter(p -> p.getEmail().length() == maxEmail)
                    .findFirst().get();
            System.out.println(patient);

            int maxPassword = patients.stream()
                    .mapToInt(p -> p.getPassword().length())
                    .max().orElse(-1);
            System.out.println("Max Password: " + maxPassword);

            int maxUserAgent = patients.stream()
                    .mapToInt(p -> p.getUserAgent().length())
                    .max().orElse(-1);
            System.out.println("Max UserAgent: " + maxUserAgent);

//            insurancePolicyCompanyRepository.saveAllAndFlush(insurancePolicyCompanies);
            patientRepository.saveAllAndFlush(patients);



            return ResponseEntity.ok("Данные о пациентах загружены");
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/bloodService")
    private ResponseEntity<String> bloodService()
    {
        try
        {
            // Создание объекта JAXBContext
            JAXBContext jaxbContext = JAXBContext.newInstance(BloodServiceDataset.class);

            // Создание объекта Unmarshaller
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            // Загрузка XML файла
            File file = new File("C:\\Users\\ASUS\\Downloads\\COVID-центр\\COVID-центр\\Ресурсы 1\\blood_services.xml");

            // Десериализация XML в объект Dataset
            BloodServiceDataset bloodServiceDataset = (BloodServiceDataset) jaxbUnmarshaller.unmarshal(file);

            // Получение списка записей пациентов
            List<BloodServicesRecord> bloodServicesRecords = bloodServiceDataset.getRecordList();

            // Создание списка пациентов для сохранения данных
            List<AnalyzerResult> analyzerResults = new ArrayList<>();

            // страховые компании
            Set<Analyzer> analyzers = new HashSet<>();

            Long startId = memberRepository.findAll().get(0).getId() - 1;

            for (BloodServicesRecord bloodServicesRecord : bloodServicesRecords)
            {
                AnalyzerResult analyzerResult = new AnalyzerResult();
                analyzerResult.setId(bloodServicesRecord.getBlood());
                analyzerResult.setService(medicalServiceRepository.findById(bloodServicesRecord.getService()).get());
                analyzerResult.setResult(bloodServicesRecord.getResult());
                analyzerResult.setCompetitionTime(bloodServicesRecord.getFinished());
                analyzerResult.setAccepted(bloodServicesRecord.getAccepted());
                analyzerResult.setStatus(AnalyzerResultStatus.valueOf(bloodServicesRecord.getStatus().toUpperCase()));
                try
                {
                    analyzerResult.setMember(memberRepository.findById(bloodServicesRecord.getUser() + startId).get());
                }
                catch (NoSuchElementException elementException)
                {
                    System.out.println("NoSuchElementException: " + bloodServicesRecord);
                    throw elementException;
                }

                Analyzer analyzer = null;
                if (analyzers.stream().noneMatch(a -> a.getName().equals(bloodServicesRecord.getAnalyzer())))
                {
                    analyzer = new Analyzer();
                    analyzer.setName(bloodServicesRecord.getAnalyzer());
                    analyzers.add(analyzer);
                }
                else
                {
                    analyzer = analyzers.stream().filter(a -> a.getName().equals(bloodServicesRecord.getAnalyzer())).findAny().get();
                }
                analyzerResult.setAnalyzer(analyzer);

                analyzerResults.add(analyzerResult);
            }

            System.out.println("Результаты анализатора");
            for (AnalyzerResult analyzerResult : analyzerResults)
            {
                System.out.println(analyzerResult);
            }

            System.out.println("----");
            System.out.println("Анализаторы:");
            for (Analyzer analyzer : analyzers)
            {
                System.out.println(analyzer);
            }

            analyzerRepository.saveAllAndFlush(analyzers);
            analyzerResultRepository.saveAllAndFlush(analyzerResults);

            return ResponseEntity.ok("Данные о результатах анализаторов загружены");
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
