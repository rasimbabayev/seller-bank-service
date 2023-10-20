package com.reausnta.selllerbank.controller;


import com.reausnta.selllerbank.model.dto.NotificationDto;
import com.reausnta.selllerbank.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/{id}")
    public void moneySentNotification(@PathVariable String id, @RequestBody NotificationDto notificationDto) {
        notificationService.moneySentNotification(id, notificationDto);
    }
}
