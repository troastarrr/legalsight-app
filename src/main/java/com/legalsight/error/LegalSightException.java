package com.legalsight.error;

import lombok.Getter;

import java.util.UUID;

public class LegalSightException extends RuntimeException {

    @Getter
    private final String uuid;

    public LegalSightException(String message) {
        super(message);
        this.uuid = UUID.randomUUID().toString();
    }
}
