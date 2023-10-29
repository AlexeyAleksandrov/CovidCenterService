package ru.alexeyaleksandrov.covidcenterservice.imports;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class BloodServicesRecord
{
    @XmlElement(name = "blood")
    private Long blood;

    @XmlElement(name = "service")
    private Long service;

    @XmlElement(name = "result")
    private Double result;

    @XmlElement(name = "finished")
    private Long finished;

    @XmlElement(name = "accepted")
    private Boolean accepted;

    @XmlElement(name = "status")
    private String status;

    @XmlElement(name = "analyzer")
    private String analyzer;

    @XmlElement(name = "user")
    private Long user;
}
