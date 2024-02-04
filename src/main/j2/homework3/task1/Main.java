package j2.homework3.task1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import j2.homework3.task1.csv.Securities2Csv;
import j2.homework3.task1.json.Moex;
import j2.homework3.task1.json.Securities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    // https://iss.moex.com/iss/securities.json?q=Yandex
    static final String path = "https://iss.moex.com/iss/securities.json?q=";
    /**
     * String - name columns
     * Integer - index in data
     */
    static final Map<String, Integer> csvColumns = new HashMap<>();

    static {
        csvColumns.put("secid", -1);
        csvColumns.put("shortname", -1);
        csvColumns.put("regnumber", -1);
        csvColumns.put("name", -1);
        csvColumns.put("emitent_title", -1);
        csvColumns.put("emitent_inn", -1);
        csvColumns.put("emitent_okpo", -1);
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Неправильные аргументы.");
            return;
        }

        String searchQuery = args[0];

        try {
            System.out.println("-------------------------------");
            System.out.println("Получение строки JSON с сайта");

            URI uri = new URI(path + searchQuery);
            System.out.println("Requesting " + uri);
            String result = getByHttp(uri);


            System.out.println("-------------------------------");
            System.out.println("Сохранение строки в объект");

            Moex moex = getSecuritiesFromJson(result);
            System.out.println();
            System.out.println(moex.toString());


            System.out.println("-------------------------------");
            System.out.println("Фильтрация значений объекта");

            ArrayList<MoexData> dataToCsv = filterData(moex);


            System.out.println("-------------------------------");
            System.out.println("Сохранение в csv формат");

            saveToCsv(dataToCsv, searchQuery);

        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void saveToCsv(ArrayList<MoexData> filteredOnFields, String path) {
        // путь до домашней директории пользователя
        // String userHome = System.getProperty("user.home");
        // путь если сохранять в Java23_HomeTasks
        String userHome = System.getProperty("user.dir");

        // создание директории в случае её отсутствия
        String directoryPath = userHome + "\\MOEX securities\\";
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Директория создана: " + directoryPath);
            } else {
                System.err.println("Ошибка при создании директории: " + directoryPath);
                return;
            }
        }


        String pathToFile = directoryPath + path;
        try (Writer writer = new FileWriter(pathToFile)) {
            if (Securities2Csv.save(filteredOnFields, writer)) {
                System.out.println();
            } else {
                System.err.println("Неверный формат данных для записи");
            }

        } catch (IOException e) {
            System.err.printf("Ошибка записи в файл %s: %s\n", path, e.getMessage());
        }
    }


    private static ArrayList<MoexData> filterData(Moex moex) {
        Securities securities = moex.getSecurities();

        ArrayList<ArrayList<Object>> filteredOnTraded = FilterArrayList.filterOnTraded(securities.getColumns(),
                securities.getData(), 1);

        System.out.println(filteredOnTraded);

        ArrayList<MoexData> selectedOnFields = FilterArrayList.selectOnFields(securities.getColumns(),
                filteredOnTraded, csvColumns);

        System.out.println(selectedOnFields);
        return selectedOnFields;
    }

    private static String getByHttp(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder(uri)
                .build();
        HttpClient client = HttpClient
                .newBuilder()
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status: " + response.statusCode());
        String body = response.body();
        System.out.println(body);
        return body;
    }

    private static Moex getSecuritiesFromJson(String body) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        return mapper.readValue(body, Moex.class);
    }
}
