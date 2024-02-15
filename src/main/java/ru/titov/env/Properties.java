package ru.titov.env;

import java.util.Arrays;
import java.util.List;

public enum Properties {
    LOCATION("-l", "l", "location", "-location"),
    TOPIC("-t", "t", "topic", "-topic"),
    FILE("-f", "f", "file", "-file");

    private List<String> values;

    Properties(String... args) {
        this.values = Arrays.asList(args);
    }

    public List<String> getValues() {
        return values;
    }
}
