package ru.titov;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JmsSender {

    public static void main(String... args) {
        Map<String, String> properties = new HashMap<>();
        properties = getArguments(args);
        System.out.println("Start application");
        System.out.println("Search file");


    }

    private static Map<String, String> getArguments(String... args) {
        List<String> kafkaLocation = Arrays.asList("-l", "l", "location", "-location");
        List<String> topic = Arrays.asList("-t", "t", "topic", "-topic");
        List<String> file = Arrays.asList("-f", "f", "file", "-file");
        Map<String, String> properties = new HashMap<>();

        for (var i = 0; i <= args.length - 2; i = i + 2) {
            if (kafkaLocation.contains(args[i])) {
                properties.put("location", args[i + 1]);
                continue;
            }

            if (topic.contains(args[i])) {
                properties.put("topic", args[i + 1]);
                continue;
            }

            if (file.contains(args[i])) {
                properties.put("file", args[i + 1]);
            }
        }

        System.out.println("Get next properties:");
        properties.forEach((k, v) -> System.out.println(k + "->" + v));

        validProperties(properties);


        return properties;
    }

    private static void validProperties(Map<String, String> properties) {
        if (properties.isEmpty() || properties.keySet().size() != 3) {
            throw new RuntimeException("Error: parameters are specified incorrectly");
        }
    }
}
