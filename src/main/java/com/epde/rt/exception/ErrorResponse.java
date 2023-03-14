package com.epde.rt.exception;

import lombok.*;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
}
