package ru.titov.config;

import com.google.gson.JsonSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import ru.titov.serializer.ValueSerializer;

import java.util.Properties;

public class KafkaProducerConfig {

    public static Producer<String, Object> createProducer(String kafkaServer) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "TestProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new KafkaProducer<>(props, new StringSerializer(), new ValueSerializer<>());
    }
}
