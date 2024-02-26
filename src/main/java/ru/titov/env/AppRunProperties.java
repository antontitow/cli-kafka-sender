package ru.titov.env;

import java.util.Arrays;
import java.util.List;

public enum AppRunProperties {
    KAFKA_SERVERS("-l", "l", "location", "-location"),
    TOPIC("-t", "t", "topic", "-topic"),
    FILE_TO_PARSE("-f", "f", "file", "-file");

    private List<String> values;

    AppRunProperties(String... args) {
        this.values = Arrays.asList(args);
    }

    public List<String> getValues() {
        return values;
    }
}
