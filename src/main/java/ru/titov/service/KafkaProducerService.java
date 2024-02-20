package ru.titov.service;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import ru.titov.config.KafkaProducerConfig;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class KafkaProducerService {
    private final Producer<String, Object> producer;

    public KafkaProducerService(String kafkaServer) {
        this.producer = KafkaProducerConfig.createProducer(kafkaServer);
    }

    private void send(String topic, Object object) throws ExecutionException, InterruptedException {

        long time = System.currentTimeMillis();
        final ProducerRecord<String, Object> record = new ProducerRecord<>(topic, String.valueOf(time), object);

        RecordMetadata metadata = this.producer.send(record).get();
        long elapsedTime = System.currentTimeMillis() - time;

        System.out.printf("sent record(key=%s value=%s) " +
                        "meta(partition=%d, offset=%d) time=%d\n",
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
                    System.out.printf("Object %s not sent", obj);
                    throw new RuntimeException(e);
                }
            });
        } finally {
            this.producer.flush();
            this.producer.close();
        }
    }
}
