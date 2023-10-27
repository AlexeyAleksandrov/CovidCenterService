package ru.alexeyaleksandrov.covidcenterservice.imports;

import ru.alexeyaleksandrov.covidcenterservice.models.insurance.InsurancePolicyCompany;
import ru.alexeyaleksandrov.covidcenterservice.models.users.Patient;
import ru.alexeyaleksandrov.covidcenterservice.models.insurance.SocialType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PatientsImport
{
    public static void main(String[] args)
    {
        try
        {
            // Создание объекта JAXBContext
            JAXBContext jaxbContext = JAXBContext.newInstance(Dataset.class);

            // Создание объекта Unmarshaller
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            // Загрузка XML файла
            File file = new File("C:\\Users\\ASUS\\Downloads\\COVID-центр\\COVID-центр\\Ресурсы 1\\patients.xml");

            // Десериализация XML в объект Dataset
            Dataset dataset = (Dataset) jaxbUnmarshaller.unmarshal(file);

            // Получение списка записей пациентов
            List<Record> records = dataset.getRecordList();

            // Создание списка пациентов для сохранения данных
            List<Patient> patients = new ArrayList<>();

            // страховые компании
            Set<InsurancePolicyCompany> insurancePolicyCompanies = new HashSet<>();

            // Преобразование записей пациентов в объекты Patient
            for (Record record : records)
            {
                Patient patient = new Patient();
                patient.setId(record.getId());
                patient.setFullName(record.getFullName());
                // Установка остальных полей пациента
                patient.setPassport(record.getPassportS() + record.getPassportN());
                patient.setPhoneNumber(record.getPhone());
                patient.setEmail(record.getEmail());
                patient.setLogin(record.getLogin());
                patient.setPassword(record.getPassword());
                patient.setBirthday(record.getBirthdateTimestamp());
                patient.setSocialSecNumber(record.getSocialSecNumber());
                patient.setSocialType(SocialType.valueOf(record.getSocialType().toUpperCase()));
                patient.setCountry(record.getCountry());
                patient.setIpAddress(record.getIpAddress());
                patient.setUserAgent(record.getUserAgent());

                InsurancePolicyCompany insurancePolicy = insurancePolicyCompanies.stream().filter(insurancePolicyCompany -> insurancePolicyCompany.getName().equals(record.getInsuranceName())).findFirst().orElse(null);
                if(insurancePolicy == null)
                {
                    // Создание объекта InsurancePolicyCompany и установка его для пациента
                    insurancePolicy = new InsurancePolicyCompany();
                    insurancePolicy.setName(record.getInsuranceName());
                    insurancePolicy.setAddress(record.getInsuranceAddress());
                    insurancePolicy.setIndividualTaxNumber(record.getInsuranceInn());
                    insurancePolicy.setPolicyCode(record.getInsurancePc());
                    insurancePolicy.setBankIdentificationCode(record.getInsuranceBik());

                    insurancePolicyCompanies.add(insurancePolicy);
                }
                patient.setInsurancePolicy(insurancePolicy);

                patients.add(patient);
            }

            // Вывод данных пациентов
            for (Patient patient : patients)
            {
                System.out.println(patient);
            }

            // Вывод данных компаний
            for (InsurancePolicyCompany company : insurancePolicyCompanies)
            {
                System.out.println(company);
            }
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
    }
}

