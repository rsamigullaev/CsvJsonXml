package org.example.sf;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Util {
    private static final File output = new File("output");

    @SuppressWarnings({"ConstantConditions", "UnnecessaryLocalVariable"})
    public static File getResourceFile(String filename) {
        String filePath = Util.class.getClassLoader().getResource(filename).getFile();
        File file = new File(filePath);
        return file;
    }

    public static <T> String listToJson(List<T> list) {
        Type listType = new TypeToken<List<T>>() {}.getType();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(list, listType);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void writeString(
            String content,
            String filename
    ) throws IOException {
        if (!output.exists()) output.mkdir();

        FileWriter writer = new FileWriter(new File(output, filename));
        writer.write(content);
        writer.close();
    }
}
