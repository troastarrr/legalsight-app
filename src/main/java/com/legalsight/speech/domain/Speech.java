package com.legalsight.speech.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class Speech {
    private String id;
    @NotEmpty
    private String speechText;
    @NotEmpty
    private String author;
    @NotEmpty
    private String subjectArea;
    @Valid
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate speechDate;
}
