package ru.alexeyaleksandrov.covidcenterservice.imports;

import ru.alexeyaleksandrov.covidcenterservice.models.services.Analyzer;
import ru.alexeyaleksandrov.covidcenterservice.models.services.AnalyzerResult;
import ru.alexeyaleksandrov.covidcenterservice.models.services.AnalyzerResultStatus;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BloodServiceImport
{
    public /*static*/ void main(String[] args)
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

            for (BloodServicesRecord bloodServicesRecord : bloodServicesRecords)
            {
                AnalyzerResult analyzerResult = new AnalyzerResult();
                analyzerResult.setId(bloodServicesRecord.getBlood());
                analyzerResult.setService(null);
                analyzerResult.setResult(bloodServicesRecord.getResult());
                analyzerResult.setCompetitionTime(bloodServicesRecord.getFinished());
                analyzerResult.setAccepted(bloodServicesRecord.getAccepted());
                analyzerResult.setStatus(AnalyzerResultStatus.valueOf(bloodServicesRecord.getStatus().toUpperCase()));
                analyzerResult.setUser(null);

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
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
    }
}
