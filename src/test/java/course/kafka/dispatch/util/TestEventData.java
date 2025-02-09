package course.kafka.dispatch.util;

import course.kafka.dispatch.message.OrderCreated;

import java.util.UUID;

public class TestEventData {

    public static OrderCreated buildOrderCreatedEvent(UUID orderId, String item){
        return OrderCreated.builder()
                .orderId(orderId)
                .item(item)
                .build();
    }
}
