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
            Set<InsurancePolicyCompany> insurancePolicyCompanies = new HashSet<>();

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
                patient.setPassword(patientRecord.getPassword());
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

