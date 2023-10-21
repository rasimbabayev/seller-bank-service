package com.reausnta.selllerbank.controller;


import com.reausnta.selllerbank.model.dto.Message;
import com.reausnta.selllerbank.persistent.RefSessionHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notification")
public class PaymentController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final RefSessionHolder refSessionHolder;

    @PostMapping("/{reference}")
    public void moneySentNotification(@PathVariable String reference) {
        refSessionHolder.getSessions(reference).forEach(session -> System.out.println("sid " + session));
        refSessionHolder.getSessions(reference)
                .forEach(sessionId ->
                        simpMessagingTemplate.convertAndSendToUser(
                                sessionId,
                                "/notifications",
                                new Message("Payment has just been accepted", null),
                                createHeaders(sessionId))
                );

        refSessionHolder.removeReference(reference);
    }

    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }
}
