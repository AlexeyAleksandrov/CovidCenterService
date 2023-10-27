package ru.alexeyaleksandrov.covidcenterservice.imports;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "dataset")
@XmlAccessorType(XmlAccessType.FIELD)
public class Dataset {

    @XmlElement(name = "record")
    private List<Record> recordList;

    public List<Record> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
    }
}
