package com.innopolis.eventgo.db.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ErrorEntity {
    int code;
    String message;
}
