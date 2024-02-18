package ru.titov.parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class JsonParser {
    public static List<Object> parseFile(String path) {
        try {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Object>>() {
            }.getType();
            List<Object> gsonList = gson.fromJson(new FileReader(path), listType);
            gsonList.forEach(r -> System.out.println(r.toString()));

            return gsonList;
        } catch (Exception e) {
            throw new RuntimeException("File parsing error");
        }
    }
}
