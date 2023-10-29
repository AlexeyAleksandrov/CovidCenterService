package ru.alexeyaleksandrov.covidcenterservice.imports;

import lombok.Data;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class PatientRecord
{

    @XmlElement(name = "id")
    private Long id;

    @XmlElement(name = "full_name")
    private String fullName;

    @XmlElement(name = "login")
    private String login;

    @XmlElement(name = "pwd")
    private String password;

    @XmlElement(name = "guid")
    private String guid;

    @XmlElement(name = "email")
    private String email;

    @XmlElement(name = "social_sec_number")
    private Long socialSecNumber;

    @XmlElement(name = "ein")
    private String ein;

    @XmlElement(name = "social_type")
    private String socialType;

    @XmlElement(name = "phone")
    private String phone;

    @XmlElement(name = "passport_s")
    private String passportS;

    @XmlElement(name = "passport_n")
    private String passportN;

    @XmlElement(name = "birthdate_timestamp")
    private Long birthdateTimestamp;

    @XmlElement(name = "country")
    private String country;

    @XmlElement(name = "insurance_name")
    private String insuranceName;

    @XmlElement(name = "insurance_address")
    private String insuranceAddress;

    @XmlElement(name = "insurance_inn")
    private Long insuranceInn;

    @XmlElement(name = "ipadress")
    private String ipAddress;

    @XmlElement(name = "insurance_pc")
    private Long insurancePc;

    @XmlElement(name = "insurance_bik")
    private Long insuranceBik;

    @XmlElement(name = "ua")
    private String userAgent;
}
