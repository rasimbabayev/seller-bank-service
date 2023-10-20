package com.reausnta.selllerbank.model.dto;

import com.reausnta.selllerbank.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {
    private Status status;
    private String content;
}
