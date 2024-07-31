package com.example.Library.Management.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private String message;
    private int statusCode;
    private long occurredAt;
    private String traceError;
}
