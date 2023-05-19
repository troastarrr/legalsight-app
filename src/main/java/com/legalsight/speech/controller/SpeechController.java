package com.legalsight.speech.controller;

import com.legalsight.speech.domain.Speech;
import com.legalsight.speech.dto.SpeechListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@RequestMapping("/speeches/v1")
@Tag(name = "speeches", description = "This endpoint allows to manage alert speeches transactions.")
public interface SpeechController {


    @GetMapping("/{id}")
    @Operation(summary = "Find Speech API", description = "Find an existing speech", tags = "speech")
    ResponseEntity<Speech> find(@PathVariable("id") final String id);

    @PostMapping
    @Operation(summary = "Create Speech API", description = "Create a new speech", tags = "speech")
    ResponseEntity<Speech> add(@Valid @RequestBody final Speech speech);

    @PutMapping("/{id}")
    @Operation(summary = "Update Speech API", description = "Update an existing speech.", tags = "speech")
    ResponseEntity<Speech> update(@PathVariable("id") final String id, @Valid @RequestBody final Speech speech);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Speech API", description = "Update an existing speech.", tags = "speech")
    ResponseEntity<Void> delete(@PathVariable("id") final String id);

    @GetMapping("/search")
    @Operation(summary = "Search Speech API", description = "Returns a list of speech based on the filter", tags = "speech")
    ResponseEntity<SpeechListResponse> search(@RequestParam(value = "author", required = false) final String author,
                                              @RequestParam(value = "speech_text", required = false) final String speechText,
                                              @RequestParam(value = "subject_area", required = false) final String subjectArea,
                                              @RequestParam(value = "speech_date", required = false) final LocalDate speechDate,
                                              @RequestParam(value = "per_page", required = false, defaultValue = "10") final int perPage,
                                              @RequestParam(value = "page", required = false, defaultValue = "1") final int page,
                                              @RequestParam(value = "sort_by", required = false, defaultValue = "author") final String sortBy,
                                              @RequestParam(value = "sort_dir", required = false, defaultValue = "desc") final String sortDir);

    @GetMapping
    @Operation(summary = "Find All Speech API", description = "Find all speech", tags = "speeches")
    ResponseEntity<SpeechListResponse> findAll(@RequestParam(value = "per_page", required = false, defaultValue = "50") final int perPage,
                                               @RequestParam(value = "page", required = false, defaultValue = "1") final int page,
                                               @RequestParam(value = "sort_by", required = false, defaultValue = "author") final String sortBy,
                                               @RequestParam(value = "sort_dir", required = false, defaultValue = "desc") final String sortDir);
}