package com.epde.rt.exception.generic;

import lombok.*;
@Data
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
}
