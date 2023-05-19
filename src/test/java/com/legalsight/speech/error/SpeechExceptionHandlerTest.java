package com.legalsight.speech.error;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class SpeechExceptionHandlerTest {

    @Test
    @DisplayName("Handle SpeechNotFoundException - Returns NotFound Response with Error Message")
    void handleSpeechNotFoundException_ReturnsNotFoundResponseWithErrorMessage() {
        // Given
        SpeechNotFoundException exception = new SpeechNotFoundException("Speech not found");
        SpeechExceptionHandler speechExceptionHandler = new SpeechExceptionHandler();

        // When
        ResponseEntity<SpeechError> responseEntity = speechExceptionHandler.handleSpeechNotFoundException(exception);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().errorCode()).isEqualTo(SpeechErrorCode.SPEECH_NOT_FOUND);
        assertThat(responseEntity.getBody().errorMessages()).containsOnly("Speech not found");
    }

    @Test
    @DisplayName("Handle Generic Exception - Returns InternalServerError Response with Error Message")
    void handleGenericException_ReturnsInternalServerErrorResponseWithErrorMessage() {
        // Given
        Exception exception = new Exception("Unknown error");
        SpeechExceptionHandler speechExceptionHandler = new SpeechExceptionHandler();

        // When
        ResponseEntity<SpeechError> responseEntity = speechExceptionHandler.handleGenericException(exception);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().errorCode()).isEqualTo(SpeechErrorCode.SERVER_ERROR);
        assertThat(responseEntity.getBody().errorMessages()).containsOnly("Unknown error");
    }

}
