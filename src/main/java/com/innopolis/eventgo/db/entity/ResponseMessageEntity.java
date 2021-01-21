package com.innopolis.eventgo.db.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseMessageEntity {
    int code;
    String message;
}
