package com.github.ratel.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UnverifiedException extends RuntimeException {
    public UnverifiedException(String message) { super(message);}

}
