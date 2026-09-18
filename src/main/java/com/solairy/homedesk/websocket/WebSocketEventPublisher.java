package com.solairy.homedesk.websocket;

import com.solairy.homedesk.websocket.dto.LowStockEvent;
import com.solairy.homedesk.websocket.dto.StockUpdateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebSocketEventPublisher {

    private final SimpMessagingTemplate messagingTemplate;

    public void stockUpdate(StockUpdateEvent event) {
        messagingTemplate.convertAndSend(
                "/topic/stock",
                event);
    }

    public void lowStock(LowStockEvent event) {
        messagingTemplate.convertAndSend(
                "/topic/low-stock",
                event);
    }
}
