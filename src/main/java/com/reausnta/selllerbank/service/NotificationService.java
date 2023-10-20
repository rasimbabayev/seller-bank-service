package com.reausnta.selllerbank.service;

import com.reausnta.selllerbank.model.dto.NotificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public void moneySentNotification(String id, NotificationDto notificationDto) {

        String message = "Payment " + notificationDto.getStatus() + ", " + notificationDto.getContent();

        messagingTemplate.convertAndSendToUser(id,"/topic/private-notifications", message);
    }
}
