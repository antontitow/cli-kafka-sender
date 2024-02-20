package ru.titov.parser;

import ru.titov.env.Properties;

import java.util.HashMap;
import java.util.Map;

public class ArgumentParser {
    private static final String GET_NEXT_PROPERTIES = "Get next properties:";
    private static final String INVALID_PARAMS = "Error: parameters are specified incorrectly";
    private static final String SEPARATOR = "->";

    public static Map<String, String> getArguments(String... args) {
        Map<String, String> properties = new HashMap<>();

        for (var i = 0; i <= args.length - 2; i = i + 2) {
            if (Properties.KAFKA_SERVERS.getValues().contains(args[i])) {
                properties.put(Properties.KAFKA_SERVERS.name(), args[i + 1]);
                continue;
            }

            if (Properties.TOPIC.getValues().contains(args[i])) {
                properties.put(Properties.TOPIC.name(), args[i + 1]);
                continue;
            }

            if (Properties.FILE_TO_PARSE.getValues().contains(args[i])) {
                properties.put(Properties.FILE_TO_PARSE.name(), args[i + 1]);
            }
        }

        System.out.println(GET_NEXT_PROPERTIES);
        properties.forEach((k, v) -> System.out.println(k + SEPARATOR + v));

        validProperties(properties);

        return properties;
    }

    private static void validProperties(Map<String, String> properties) {
        if (properties.isEmpty() || properties.keySet().size() != 3) {
            throw new RuntimeException(INVALID_PARAMS);
        }
    }
}
