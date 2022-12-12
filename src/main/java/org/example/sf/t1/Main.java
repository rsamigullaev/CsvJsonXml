package org.example.sf.t1;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.example.sf.Employee;
import org.example.sf.Util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, CsvValidationException {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String filename = "data.csv";
        List<Employee> parsedList = parseCSV(columnMapping, filename, Employee.class);
        String parsedContent = Util.listToJson(parsedList);
        Util.writeString(parsedContent, "csvToJson.json");
    }

    private static <T> List<T> parseCSV(
            String[] columnMapping,
            String filename,
            Class<T> type
    ) throws IOException {
        File file = Util.getResourceFile(filename);

        FileReader fileReader = new FileReader(file);
        CSVReader reader = new CSVReader(fileReader);

        ColumnPositionMappingStrategy<T> cpms = new ColumnPositionMappingStrategy<>();
        cpms.setType(type);
        cpms.setColumnMapping(columnMapping);

        CsvToBean<T> ctb = new CsvToBeanBuilder<T>(reader)
                .withMappingStrategy(cpms)
                .build();
        List<T> result = ctb.parse();

        reader.close();
        return result;
    }
}
