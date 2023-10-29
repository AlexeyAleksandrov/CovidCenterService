package ru.alexeyaleksandrov.covidcenterservice.imports;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "dataset")
@XmlAccessorType(XmlAccessType.FIELD)
public class PatientDataset
{

    @XmlElement(name = "record")
    private List<PatientRecord> patientRecordList;

    public List<PatientRecord> getRecordList() {
        return patientRecordList;
    }

    public void setRecordList(List<PatientRecord> patientRecordList) {
        this.patientRecordList = patientRecordList;
    }
}
