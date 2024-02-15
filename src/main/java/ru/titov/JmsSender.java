package ru.titov;

import ru.titov.env.Properties;

import java.util.HashMap;
import java.util.Map;

public class JmsSender {

    public static void main(String... args) {
        Map<String, String> properties = getArguments(args);
        System.out.println("Start application");
        System.out.println("Search file" + properties.get(Properties.FILE.name()));
    }

    private static Map<String, String> getArguments(String... args) {
        Map<String, String> properties = new HashMap<>();

        for (var i = 0; i <= args.length - 2; i = i + 2) {
            if (Properties.LOCATION.getValues().contains(args[i])) {
                properties.put(Properties.LOCATION.name(), args[i + 1]);
                continue;
            }

            if (Properties.TOPIC.getValues().contains(args[i])) {
                properties.put(Properties.TOPIC.name(), args[i + 1]);
                continue;
            }

            if (Properties.FILE.getValues().contains(args[i])) {
                properties.put(Properties.FILE.name(), args[i + 1]);
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
