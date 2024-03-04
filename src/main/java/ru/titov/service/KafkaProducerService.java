package ru.titov.service;

import com.google.gson.Gson;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import ru.titov.config.KafkaProducerConfig;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class KafkaProducerService {
    public static final String SEND_INFO = "sent record(key=%s value=%s) " + "meta(partition=%d, offset=%d) time=%d\n";
    public static final String SEND_ERROR = "Object %s not sent";

    private final Producer<String, String> producer;

    public KafkaProducerService(String kafkaServer) {
        this.producer = KafkaProducerConfig.createProducer(kafkaServer);
    }

    private void send(String topic, Object object) throws ExecutionException, InterruptedException {
        Gson gson = new Gson();
        long time = System.currentTimeMillis();
        String message = gson.toJson(object);
        final ProducerRecord<String, String> record = new ProducerRecord<>(topic, String.valueOf(time), message);

        RecordMetadata metadata = this.producer.send(record).get();
        long elapsedTime = System.currentTimeMillis() - time;

        System.out.printf(SEND_INFO,
                record.key(),
                record.value(),
                metadata.partition(),
                metadata.offset(),
                elapsedTime);

    }

    public void send(String topic, List<Object> objects) {
        try {
            objects.forEach(obj -> {
                try {
                    this.send(topic, obj);
                } catch (Exception e) {
                    System.out.printf(SEND_ERROR, obj);
                    throw new RuntimeException(e);
                }
            });
        } finally {
            this.producer.flush();
            this.producer.close();
        }
    }
}
