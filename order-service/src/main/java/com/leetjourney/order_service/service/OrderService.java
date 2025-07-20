package com.leetjourney.order_service.service;

import com.leetjourney.event.OrderNotification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class OrderService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, OrderNotification> kafkaTemplateOrderEmail;

    @Scheduled(fixedRate = 2000)
    public void processOrder() {
        final String orderId = UUID.randomUUID().toString();
        log.info("Processing order with ID: " + orderId);

        kafkaTemplate.send("order-placed", orderId);
        log.info("Message sent to Kafka topic: order-placed with orderId: {}", orderId);

        processOrderNotification(orderId);
    }

    private void processOrderNotification(final String orderId) {
        log.info("Sending Order notification for order with ID: " + orderId);

        final OrderNotification orderNotification = OrderNotification.builder()
                .orderId(orderId)
                .orderStatus("PLACED")
                .userId("leetjourney")
                .price(100.0)
                .productName("Product A")
                .quantity(1)
                .build();

        kafkaTemplateOrderEmail.send("order-placed-email", orderNotification);
        log.info("Message sent to Kafka topic: order-placed-email with orderId: {}", orderId);
    }
}
