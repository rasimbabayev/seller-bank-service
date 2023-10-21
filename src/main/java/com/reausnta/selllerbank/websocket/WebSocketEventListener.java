package com.reausnta.selllerbank.websocket;

import com.reausnta.selllerbank.persistent.RefSessionHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketEventListener {
    private final RefSessionHolder refSessionHolder;

    @EventListener
    private void handleSessionConnected(SessionConnectEvent event) {
        log.info("Connected session " + event.getMessage().getHeaders().get("simpSessionId"));
    }

    @EventListener
    private void handleSessionDisconnect(SessionDisconnectEvent event) {
        log.info("Disconnected session " + event.getSessionId());
        refSessionHolder.removeSession(event.getSessionId());
    }
}
