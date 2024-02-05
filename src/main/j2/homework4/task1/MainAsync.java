package j2.homework4.task1;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import j2.homework4.task1.csv.Securities2Csv;
import j2.homework4.task1.json.Moex;
import j2.homework4.task1.json.Securities;

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
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class MainAsync {
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


    // выглядит странно, потому что создание файлов после прихода всех запросов, то есть их ожидание -> не параллельно
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Неправильные аргументы.");
            return;
        }

        HttpClient client = HttpClient.newBuilder()
                .build();
        List<CompletableFuture<HttpResponse<String>>> results = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {
            System.out.println("Выполняется " + (i + 1) + " цикл запросов на сайт");

            String searchQuery = args[i];
            System.out.println("-------------------------------");
            System.out.println("Получение строки JSON с сайта" + searchQuery);

            try {
                URI uri = new URI(path + searchQuery);

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(uri)
                        .build();
                CompletableFuture<HttpResponse<String>> result = client
                        .sendAsync(request,
                                HttpResponse.BodyHandlers.ofString());

                results.add(result);

            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }

        // ожидание успешного статуса видимо, но не ожидание методов внутри
        while (results.stream().anyMatch((cf) -> !cf.isDone())) ;

        int i = 0;
        for (CompletableFuture<HttpResponse<String>> result : results) {
            int finalI = i;
            result
                    .thenAccept((response) -> {
                        if (response.statusCode() == 200) {
                            successAcceptBody(response, args[finalI]);

                        } else {
                            System.err.printf("При попытке получения json сервер вернул код %d\n",
                                    response.statusCode());
                        }
                    })
                    .exceptionally((e) -> {
                        System.err.printf("Не удалось загрузить json %s:\n",
                                e.getMessage());

                        return null;
                    });
            i++;
        }

    }

    // мейн, когда выполняется правильно-параллельно, но заканчивает работу, на самом долгом (последнем видимо)
    public static void main2(String[] args) {
        if (args.length == 0) {
            System.out.println("Неправильные аргументы.");
            return;
        }

        HttpClient client = HttpClient.newBuilder()
                .build();
        List<CompletableFuture<HttpResponse<String>>> results = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {
            System.out.println("Выполняется " + (i + 1) + " цикл запросов на сайт");

            String searchQuery = args[i];
            System.out.println("-------------------------------");
            System.out.println("Получение строки JSON с сайта");

            try {
                URI uri = new URI(path + searchQuery);
                System.out.println("Requesting " + uri);

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(uri)
                        .build();
                CompletableFuture<HttpResponse<String>> result = client
                        .sendAsync(request,
                                HttpResponse.BodyHandlers.ofString());

                results.add(result);


                int finalI = i;
                result
                        .thenAccept((response) -> {
                            if (response.statusCode() == 200) {
                                successAcceptBody(response, args[finalI]);
                            } else {
                                System.err.printf("При попытке получения json сервер вернул код %d\n",
                                        response.statusCode());
                            }
                        })
                        .exceptionally((e) -> {
                            System.err.printf("Не удалось загрузить json %s:\n",
                                    e.getMessage());
                            return null;
                        });

            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }

        // Ожидание завершения всех CompletableFuture
        CompletableFuture<Void> allOf = CompletableFuture.allOf(results.toArray(new CompletableFuture[0]));
        allOf.join();

    }

    private static void successAcceptBody(HttpResponse<String> response, String args) {

        try {
            String json = response.body();
            //System.out.printf("Строка json была успешно получена %s",
            //        json);

            System.out.println("-------------------------------");
            System.out.println("Сохранение строки: " + args + " в объект");
            Moex moex = getSecuritiesFromJson(json);
            System.out.println(moex.toString());
            System.out.println("Успешное сохранение строки: " + args + " в объект");


            System.out.println("-------------------------------");
            System.out.println("Фильтрация значений объекта " + args);
            ArrayList<MoexData> dataToCsv = filterData(moex);
            System.out.println("Успешная фильтрация значений объекта " + args);


            System.out.println("-------------------------------");
            System.out.println("Сохранение в csv формат " + args);
            saveToCsv(dataToCsv, args);
            System.out.println("Успешное сохранение в csv формат " + args);

        } catch (JsonProcessingException e) {
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

        // System.out.println(selectedOnFields);
        return FilterArrayList.selectOnFields(securities.getColumns(),
                filteredOnTraded, csvColumns);
    }

    private static Moex getSecuritiesFromJson(String body) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        return mapper.readValue(body, Moex.class);
    }
}
