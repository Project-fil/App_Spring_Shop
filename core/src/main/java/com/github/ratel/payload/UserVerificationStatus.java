package com.github.ratel.payload;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserVerificationStatus {
    VERIFIED("verified"),
    UNVERIFIED("unverified");

    private final String text;

    @Override
    public String toString() {
        return "UserVerificationStatus{ " +
                "text= " + text + '\'' +
                '}';
    }
}
