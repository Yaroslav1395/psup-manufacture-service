package sakhno.psup.manufacture_service.config.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import sakhno.psup.manufacture_service.services.kafka.KafkaTopicNames;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@Profile({"local", "test", "prod"})
public class KafkaConfig {

    private final Environment environment;

    /**
     * Метод создает словарь с конфигурацией для kafka producer
     * @return - словарь с настройками
     */
    @Bean
    Map<String, Object> producerConfigs(){
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                environment.getProperty("spring.kafka.producer.bootstrap-servers"));
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                environment.getProperty("spring.kafka.producer.key-serializer"));
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                environment.getProperty("spring.kafka.producer.value-serializer"));
        config.put(ProducerConfig.ACKS_CONFIG,
                environment.getProperty("spring.kafka.producer.acks"));
        config.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG,
                environment.getProperty("spring.kafka.producer.properties.delivery-timeout-ms"));
        config.put(ProducerConfig.LINGER_MS_CONFIG,
                environment.getProperty("spring.kafka.producer.properties.linger-ms"));
        config.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG,
                environment.getProperty("spring.kafka.producer.properties.request.timeout.ms"));
        config.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,
                environment.getProperty("spring.kafka.producer.properties.enable.idempotence"));
        config.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION,
                environment.getProperty("spring.kafka.producer.properties.max.in.flight.requests.per.connection"));
        config.put(JsonDeserializer.TRUSTED_PACKAGES,
                environment.getProperty("spring.kafka.producer.properties.spring.json.trusted.packages"));
        config.put("spring.json.type.mapping",
                environment.getProperty("spring.kafka.producer.properties.spring.json.type.mapping"));
        return config;
    }

    /**
     * Создает и настраивает бин KafkaAdmin — управляющий клиент для работы с Kafka.
     * KafkaAdmin используется Spring Kafka для автоматического создания/управления топиками,
     * проверки доступности брокеров и других административных задач.
     * @return настроенный экземпляр KafkaAdmin с конфигурацией подключения к Kafka брокерам.
     */
    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, environment.getProperty("spring.kafka.producer.bootstrap-servers"));
        return new KafkaAdmin(configs);
    }

    /**
     * Метод создает экземпляр с фабрикой по созданию kafka producer. Для создания необходим словарь с настройками
     * продюсера
     * @return - фабрика по созданию kafka producer
     */
    @Bean
    ProducerFactory<String, Object> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    /**
     * Бин KafkaTemplate для отправки сообщений типа Object с ключом типа String.
     * Использует фабрику продюсера для создания и настройки Kafka-продюсеров.
     */
    @Bean
    KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    /**
     * Бин для создания Kafka-топика с именем "product-created-events-topic".
     * Топик имеет 3 партиции, 3 реплики и настраиваемое количество реплик, которые должны быть синхронизированы для
     * обеспечения отказоустойчивости.
     */
    @Bean
    NewTopic createTopic() {
        return TopicBuilder
                .name(KafkaTopicNames.MANUFACTURE_TEST_TOPIC.getTopicName())
                .partitions(3)
                .replicas(3)
                .configs(Map.of("min.insync.replicas", "2"))
                .build();
    }
}
