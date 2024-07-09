import java.io.*;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class task3 {

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 3) {
            System.out.println("Usage: java task3.java <values_file_path> <tests_file_path> <report_file_path>");
            return;
        }

        String valuesFile = args[0];
        String testsFile = args[1];
        String reportFile = args[2];

        JSONTokener parser;

        parser = new JSONTokener(new FileReader(valuesFile));
        JSONArray valuesArray = new JSONObject(parser).getJSONArray("values");

        parser = new JSONTokener(new FileReader(testsFile));
        JSONObject testsData = new JSONObject(parser);

        // Создание словаря для значений из values.json
        Map<Long, String> valuesMap = new HashMap<>();
        if (valuesArray != null) {
            for (Object valueObj : valuesArray) {
                JSONObject valueItem = (JSONObject) valueObj;
                Long id = valueItem.getLong("id");
                String value = valueItem.getString("value");
                valuesMap.put(id, value);
            }
        }

        // Получение корневого массива tests
        JSONArray testsArray = testsData.getJSONArray("tests");
        processTestsArray(testsArray, valuesMap);

        // Запись результата в файл report.json
        try (FileWriter file = new FileWriter(reportFile)) {
            file.write(testsData.toString(3));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Рекурсивная функция для обработки массива тестов
    private static void processTestsArray(JSONArray testsArray, Map<Long, String> valuesMap) {
        for (Object testObj : testsArray) {
            JSONObject testItem = (JSONObject) testObj;
            Long testId = testItem.getLong("id");

            // Поиск соответствующего значения в valuesMap
            String value = valuesMap.get(testId);
            if (value != null) {
                testItem.put("value", value);
            }

            // Если есть вложенные values, обрабатываем их рекурсивно
            if (testItem.has("values")) {
                JSONArray nestedValuesArray = testItem.getJSONArray("values");
                processTestsArray(nestedValuesArray, valuesMap);
            }
        }
    }
}

