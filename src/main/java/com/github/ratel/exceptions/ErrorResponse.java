package com.github.ratel.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private Instant timestamp;
    private int status;
    private String error;
    private String path;

}
