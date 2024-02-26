package ru.titov.config;

import com.google.gson.JsonSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import ru.titov.enums.SslConfigParams;
import ru.titov.serializer.ValueSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class KafkaProducerConfig {
    private static final String KAFKACONFIG_PROPERTIES = "kafkaconfig.properties";
    private static final String SECURITY_PROTOCOL = "security.protocol";

    public static Producer<String, Object> createProducer(String kafkaServer) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "TestProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        props = enrichSslKafkaConnnection(props);

        return new KafkaProducer<>(props, new StringSerializer(), new ValueSerializer<>());
    }

    private static Properties enrichSslKafkaConnnection(Properties properties) {
        File configFile = new File(KAFKACONFIG_PROPERTIES);

        if (!configFile.exists()) {
            return properties;
        }

        Properties fileProperties = new Properties();

        try {
            fileProperties.load(new FileInputStream(configFile));
        } catch (IOException e) {
            return properties;
        }

        if (fileProperties.isEmpty()) {
            return properties;
        }

        String securityProtocol = fileProperties.getProperty(SECURITY_PROTOCOL);

        if (securityProtocol.isEmpty() || !securityProtocol.equals(SslConfigParams.SSL.getValue())) {
            return properties;
        }

        properties.put(SECURITY_PROTOCOL, SslConfigParams.SSL.getValue());
        properties.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, fileProperties.getProperty(SslConfigParams.SSL_TRUSTSTORE_LOCATION.getValue()));
        properties.put(SslConfigs.SSL_TRUSTSTORE_TYPE_CONFIG, fileProperties.getProperty(SslConfigParams.SSL_TRUSTSTORE_TYPE.getValue()));
        properties.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, fileProperties.getProperty(SslConfigParams.SSL_TRUSTSTORE_PASSWORD_CONFIG.getValue()));
        properties.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, fileProperties.getProperty(SslConfigParams.SSL_KEYSTORE_LOCATION_CONFIG.getValue()));
        properties.put(SslConfigs.SSL_KEYSTORE_TYPE_CONFIG, fileProperties.getProperty(SslConfigParams.SSL_KEYSTORE_TYPE_CONFIG.getValue()));
        properties.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, fileProperties.getProperty(SslConfigParams.SSL_KEYSTORE_PASSWORD_CONFIG.getValue()));
        properties.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, fileProperties.getProperty(SslConfigParams.SSL_KEY_PASSWORD_CONFIG.getValue()));

        properties.forEach((key, value) -> System.out.println("key: " + key + "   value: " + value));

        return properties;
    }
}
