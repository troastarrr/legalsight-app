package com.legalsight.speech.error;

import com.legalsight.error.LegalSightException;

public class SpeechNotFoundException extends LegalSightException {
    public SpeechNotFoundException(String message) {
        super(message);
    }
}
