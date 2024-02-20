package ru.titov.parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class JsonParser {
    private static final String FILE_PARSING_ERROR = "File parsing error";

    public static List<Object> parseFile(String path) {
        try {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Object>>() {
            }.getType();

            return gson.fromJson(new FileReader(path), listType);
        } catch (Exception e) {
            throw new RuntimeException(FILE_PARSING_ERROR);
        }
    }
}
