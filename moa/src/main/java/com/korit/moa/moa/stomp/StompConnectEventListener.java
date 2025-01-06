package com.korit.moa.moa.stomp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class StompConnectEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(StompConnectEventListener.class);

    @EventListener
    public void handleSessionConnected(SessionConnectedEvent event) {
        LOGGER.info("New WebSocket connection established");
    }

    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event) {
        LOGGER.info("WebSocket connection closed");
    }
}
