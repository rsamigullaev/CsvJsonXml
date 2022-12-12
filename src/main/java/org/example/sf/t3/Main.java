package org.example.sf.t3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import org.example.sf.Employee;
import org.example.sf.Util;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, ParseException {
        String plainJson = readString("data.json");
        List<Employee> employers = jsonToList(plainJson);
        employers.forEach(System.out::println);
    }

    private static String readString(
            String filename
    ) throws FileNotFoundException {
        File file = Util.getResourceFile(filename);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);

        List<String> lines = reader.lines().collect(Collectors.toList());

        return String.join("", lines);
    }

    @SuppressWarnings("unchecked")
    private static List<Employee> jsonToList(String json) throws ParseException {
        List<Employee> result = new ArrayList<>();
        Gson gson = new GsonBuilder().create();

        Object parsedObject = new JSONParser().parse(json);
        JSONArray array = (JSONArray) parsedObject;
        for (Object object : array) {
            Employee employee = gson.fromJson(object.toString(), Employee.class);
            result.add(employee);
        }

        return result;
    }
}
