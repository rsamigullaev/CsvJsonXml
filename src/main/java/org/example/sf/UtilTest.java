package org.example.sf;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;


class UtilTest {
    @Test
    public void testGetResourceFile_absolutePath_success() {
        String dataPath = Util.getResourceFile("data.csv").getAbsolutePath();

        String expected = "C:\\Core\\CsvJsonXml\\out\\production\\resources\\data.csv";

        Assertions.assertEquals(expected, dataPath);
    }

    @Test
    public void testGetResourceFile_emptyFilename_failure() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Util.getResourceFile(""));
    }

    @org.junit.jupiter.api.Test
    public void testListToJson_employers_success() {
        List<Employee> employers = List.of(
                new Employee(1, "John", "Smith", "USA", 25),
                new Employee(2, "Inav", "Petrov", "RU", 23)
        );
        String json = Util.listToJson(employers);

        String expected = "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"Smith\",\n" +
                "    \"country\": \"USA\",\n" +
                "    \"age\": 25\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 2,\n" +
                "    \"firstName\": \"Inav\",\n" +
                "    \"lastName\": \"Petrov\",\n" +
                "    \"country\": \"RU\",\n" +
                "    \"age\": 23\n" +
                "  }\n" +
                "]";

        Assertions.assertEquals(expected, json);
    }

}