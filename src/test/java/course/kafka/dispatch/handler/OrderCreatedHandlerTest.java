package course.kafka.dispatch.handler;

import course.kafka.dispatch.message.OrderCreated;
import course.kafka.dispatch.service.DispatchService;
import course.kafka.dispatch.util.TestEventData;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RequiredArgsConstructor
class OrderCreatedHandlerTest {

    private OrderCreatedHandler handler;
    private DispatchService serviceMock;

    @BeforeEach
    void setUp() {
        serviceMock = mock(DispatchService.class);
        handler = new OrderCreatedHandler(serviceMock);
    }

    @Test
    void listen_Success() throws Exception{
        OrderCreated testEvent = TestEventData.buildOrderCreatedEvent(randomUUID(), randomUUID().toString());
        handler.listen(testEvent);
        verify(serviceMock, times(1)).process(testEvent);
    }

    @Test
    public void listen_ServiceThrowsException() throws Exception{
        OrderCreated testEvent = TestEventData.buildOrderCreatedEvent(randomUUID(), randomUUID().toString());
        doThrow(new RuntimeException("Service failure")).when(serviceMock).process(testEvent);
        handler.listen(testEvent);
        verify(serviceMock, times(1)).process(testEvent);
    }
}