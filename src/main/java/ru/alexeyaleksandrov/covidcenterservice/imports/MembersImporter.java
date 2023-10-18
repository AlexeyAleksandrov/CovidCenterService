package ru.alexeyaleksandrov.covidcenterservice.imports;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import ru.alexeyaleksandrov.covidcenterservice.models.services.MedicalService;
import ru.alexeyaleksandrov.covidcenterservice.models.users.Member;
import ru.alexeyaleksandrov.covidcenterservice.models.users.Role;
import ru.alexeyaleksandrov.covidcenterservice.services.TimestampConverter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MembersImporter
{
    public static void main(String[] args)
    {
        MembersImporter membersImporter = new MembersImporter();
        membersImporter.importMembers();
    }

    public List<Member> importMembers()
    {
        String csvFile = "C:\\Users\\ASUS\\Downloads\\COVID-центр\\COVID-центр\\Ресурсы 1\\users.csv";

        List<Member> members = new ArrayList<>();

        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile)))
        {
            br.readLine();  // пропускаем 1ю строку
            while ((line = br.readLine()) != null)
            {
                System.out.println(line);
                int first = line.indexOf("[");  // начало массива
                int last = line.lastIndexOf("]")+1;
                String servicesJson = line.substring(first, last);
                servicesJson = servicesJson.replace("\"", "");
                line = line.replace(servicesJson, "");

//                System.out.println(line);
//                System.out.println(servicesJson);

                String[] data = line.split(",");

                Member member = new Member();
                member.setId(Long.parseLong(data[0]));  // id
                member.setFullName(data[1]);    // full_name
                member.setLogin(data[2]);   // login
                member.setPassword(data[3]);    // password // TODO: добавить шифрование
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

                // Print code list
                for (int code : codeList)
                {
                    System.out.println(code);
                }

                //                String[] servicesData = data[6].split("(?<!\\\\),");
                //                List<MedicalService> allowServices = new ArrayList<>();
                //                for (String serviceData : servicesData)
                //                {
                //                    String code = serviceData.replaceAll("\\\\\"", "\"");
                //                    int serviceCode = Integer.parseInt(code.replaceAll("\\[\\\\{\\}]", ""));
                //                    MedicalService service = new MedicalService(serviceCode);
                //                    allowServices.add(service);
                //                }
                //                member.setAllowServices(allowServices);

                //                List<Role> role = new ArrayList<>();    // TODO: загрузка списка ролей
                //                member.setRole(role.get(Integer.parseInt(data[7])));

                members.add(member);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        // Print members
        for (Member member : members)
        {
            System.out.println(member);
        }

        return members;
    }
}
