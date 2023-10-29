package ru.alexeyaleksandrov.covidcenterservice.imports;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "dataset")
@XmlAccessorType(XmlAccessType.FIELD)
public class BloodServiceDataset
{

    @XmlElement(name = "record")
    private List<BloodServicesRecord> recordList;

    public List<BloodServicesRecord> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<BloodServicesRecord> recordList) {
        this.recordList = recordList;
    }
}
