package ru.titov;

import ru.titov.env.AppRunProperties;
import ru.titov.service.KafkaProducerService;

import java.util.List;
import java.util.Map;

import static ru.titov.parser.ArgumentParser.getArguments;
import static ru.titov.parser.JsonParser.parseFile;

public class JmsSender {

    public static void main(String... args) {

        Map<String, String> properties = getArguments(args);
        List<Object> objectList = parseFile(properties.get(AppRunProperties.FILE_TO_PARSE.name()));
        new KafkaProducerService(properties.get(AppRunProperties.KAFKA_SERVERS.name())).
                send(properties.get(AppRunProperties.TOPIC.name()), objectList);
    }
}
