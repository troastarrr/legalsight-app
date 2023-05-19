package com.legalsight.speech.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class SpeechExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<SpeechError> handleSpeechNotFoundException(SpeechNotFoundException e) {
        log.warn("Speech not found error: `{}`", e.getMessage());
        SpeechError response = new SpeechError()
                .errorCode("SPEECH_NOT_FOUND")
                .messages(List.of(e.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<SpeechError> handleConstraintViolationException(MethodArgumentNotValidException e) {
        log.warn("Validation error occurred: `{}`", e.getMessage());

        List<String> errorMessages = getValidationErrorMessages(e);

        SpeechError response = new SpeechError()
                .errorCode("VALIDATION_ERROR_OCCURRED")
                .messages(errorMessages);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<SpeechError> handleGenericException(Exception e) {
        log.warn("Unknown error occurred: `{}`", e.getMessage());
        SpeechError response = new SpeechError()
                .errorCode("UNKNOWN_ERROR_OCCURRED")
                .messages(List.of(e.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private List<String> getValidationErrorMessages(MethodArgumentNotValidException e) {
        List<String> errorMessages = new ArrayList<>();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            String errorMessage = buildErrorMessage(fieldError.getField(), fieldError.getDefaultMessage());
            errorMessages.add(errorMessage);
        }
        return errorMessages;
    }

    private String buildErrorMessage(String fieldName, String message) {
        return String.format("%s %s", fieldName, message);
    }

}
