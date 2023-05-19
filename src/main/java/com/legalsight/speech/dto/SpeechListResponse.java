package com.legalsight.speech.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.legalsight.speech.domain.Speech;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(fluent = true, chain = true)
@Getter(onMethod = @__(@JsonProperty))
@Setter
public class SpeechListResponse {
    private final List<Speech> data;
    private long totalElements;
    private long totalPages;
    private long currentPage;

    public SpeechListResponse(final List<Speech> data) {
        this.data = data;
    }
}

