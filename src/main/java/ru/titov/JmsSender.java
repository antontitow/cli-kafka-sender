package ru.titov;

import ru.titov.env.Properties;
import ru.titov.service.KafkaProducerService;

import java.util.List;
import java.util.Map;

import static ru.titov.parser.ArgumentParser.getArguments;
import static ru.titov.parser.JsonParser.parseFile;

public class JmsSender {

    public static void main(String... args) {
        Map<String, String> properties = getArguments(args);
        System.out.println("Search file " + properties.get(Properties.FILE_TO_PARSE.name()));

        List<Object> objectList = parseFile(properties.get(Properties.FILE_TO_PARSE.name()));

        new KafkaProducerService(properties.get(Properties.KAFKA_SERVERS.name())).
                send(properties.get(Properties.TOPIC.name()), objectList);

    }
}
