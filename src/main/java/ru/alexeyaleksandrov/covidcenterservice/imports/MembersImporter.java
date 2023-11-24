package ru.alexeyaleksandrov.covidcenterservice.imports;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import ru.alexeyaleksandrov.covidcenterservice.models.users.User;
import ru.alexeyaleksandrov.covidcenterservice.services.TimestampConverter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MembersImporter
{
    public /*static*/ void main(String[] args)
    {
        MembersImporter membersImporter = new MembersImporter();
        membersImporter.importMembers();
    }

    public List<User> importMembers()
    {
        String csvFile = "C:\\Users\\ASUS\\Downloads\\COVID-центр\\COVID-центр\\Ресурсы 1\\users.csv";

        List<User> users = new ArrayList<>();

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
                line = line.replace(servicesJson, "");
                servicesJson = servicesJson.replace("\"", "");

                System.out.println(line);
                System.out.println(servicesJson);

                String[] data = line.split(",");

                System.out.println(Arrays.toString(data));

                User user = new User();
                user.setId(Long.parseLong(data[0]));  // id
                user.setFullName(data[1]);    // full_name
                user.setLogin(data[2]);   // login
                user.setPassword(data[3]);    // password // TODO: добавить шифрование
                user.setIp(data[4]);  // ip

                long timestamp = TimestampConverter.convertMMddyyyyToTimestamp(data[5]);
                user.setLastEnterTime(timestamp);     // last_enter

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
                codeList = List.copyOf(codeSet);

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
                System.out.println("data = " + data[7]);
                System.out.println(Integer.parseInt(data[7]));

                users.add(user);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        // Print members
        for (User user : users)
        {
            System.out.println(user);
        }

        return users;
    }
}
