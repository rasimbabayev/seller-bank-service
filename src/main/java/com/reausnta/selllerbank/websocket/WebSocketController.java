package com.reausnta.selllerbank.websocket;

import com.reausnta.selllerbank.persistent.RefSessionHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WebSocketController {
    private final RefSessionHolder refSessionHolder;

    @MessageMapping("/payment/{reference}")
    public void initPaymentStatusAwait(@DestinationVariable String reference, SimpMessageHeaderAccessor accessor) {
        log.info("reference: " + reference);
        log.info("session id:" + accessor.getSessionId());
        refSessionHolder.addRefSessionLink(reference, accessor.getSessionId());
    }
}
