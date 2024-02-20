package microservice.orchestrator.config.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    private static final Integer PARTITION_COUNT = 1;

    private static final Integer REPLICA_COUNT = 1;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerProperties());
    }

    private Map<String, Object> consumerProperties() {

        var properties = new HashMap<String, Object>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);

        return properties;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerProperties());
    }

    private Map<String, Object> producerProperties() {

        var properties = new HashMap<String, Object>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return properties;
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public NewTopic startSagaTopic() {
        return buildTopic(ETopics.START_SAGA.getTopic());
    }

    @Bean
    public NewTopic orchestratorTopic() {
        return buildTopic(ETopics.BASE_ORCHESTRATOR.getTopic());
    }

    @Bean
    public NewTopic finishSuccessTopic() {
        return buildTopic(ETopics.FINISH_SUCCESS.getTopic());
    }

    @Bean
    public NewTopic finishFailTopic() {
        return buildTopic(ETopics.FINISH_FAIL.getTopic());
    }

    @Bean
    public NewTopic timeValidationSuccessTopic() {
        return buildTopic(ETopics.TIME_VALIDATION_SUCCESS.getTopic());
    }

    @Bean
    public NewTopic timeValidationFailTopic() {
        return buildTopic(ETopics.TIME_VALIDATION_FAIL.getTopic());
    }

    @Bean
    public NewTopic pagamentoSuccessTopic() {
        return buildTopic(ETopics.PAGAMENTO_SUCCESS.getTopic());
    }

    @Bean
    public NewTopic pagamentoFailTopic() {
        return buildTopic(ETopics.PAGAMENTO_FAIL.getTopic());
    }

    @Bean
    public NewTopic torneioSuccessTopic() {
        return buildTopic(ETopics.TORNEIO_SUCCESS.getTopic());
    }

    @Bean
    public NewTopic torneioFailTopic() {
        return buildTopic(ETopics.TORNEIO_FAIL.getTopic());
    }

    @Bean
    public NewTopic notifyEndingTopic() {
        return buildTopic(ETopics.NOTIFY_ENDING.getTopic());
    }

    private NewTopic buildTopic(String name) {

        return TopicBuilder
            .name(name)
            .replicas(REPLICA_COUNT)
            .partitions(PARTITION_COUNT)
            .build();
    }
}

