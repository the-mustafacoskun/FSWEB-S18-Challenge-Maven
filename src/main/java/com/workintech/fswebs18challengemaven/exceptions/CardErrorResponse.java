package com.workintech.fswebs18challengemaven.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardErrorResponse {
    private int status;
    private String message;
    private long timestamp;

    public CardErrorResponse(String expectedMessage) {
        this.status = 400;
        this.message = expectedMessage;
        this.timestamp = System.currentTimeMillis();

    }
}
