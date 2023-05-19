package com.legalsight.speech.controller.impl;

import com.legalsight.speech.controller.SpeechController;
import com.legalsight.speech.domain.Speech;
import com.legalsight.speech.dto.SpeechFilterRequest;
import com.legalsight.speech.dto.SpeechListResponse;
import com.legalsight.speech.service.SpeechService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@AllArgsConstructor
public class SpeechControllerImpl implements SpeechController {

    private final SpeechService service;

    @Override
    public ResponseEntity<Speech> find(final String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Override
    public ResponseEntity<Speech> add(final Speech speech) {
        return ResponseEntity.ok(service.add(speech));
    }

    @Override
    public ResponseEntity<Speech> update(final String id, final Speech speech) {
        speech.setId(id);
        return ResponseEntity.ok(service.update(speech));
    }

    @Override
    public ResponseEntity<Void> delete(final String id) {
        service.deleteSpeech(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<SpeechListResponse> search(final String author,
                                                     final String speechText,
                                                     final String subjectArea,
                                                     final LocalDate speechDate,
                                                     final int perPage,
                                                     final int page,
                                                     final String sortBy,
                                                     final String sortDir) {
        Speech speech = new Speech();
        speech.setSpeechDate(speechDate);
        speech.setSpeechText(speechText);
        speech.setAuthor(author);
        speech.setSubjectArea(subjectArea);
        SpeechFilterRequest request = new SpeechFilterRequest(speech);
        request.setPage(page);
        request.setPerPage(perPage);
        request.setSortBy(sortBy);
        request.setSortDir(sortDir);
        return ResponseEntity.ok(service.findByFilter(request));
    }

    @Override
    public ResponseEntity<SpeechListResponse> findAll(final int perPage,
                                                      final int page,
                                                      final String sortBy,
                                                      final String sortDir) {
        SpeechFilterRequest request = new SpeechFilterRequest();
        request.setPage(page);
        request.setPerPage(perPage);
        request.setSortBy(sortBy);
        request.setSortDir(sortDir);
        return ResponseEntity.ok(service.findByFilter(request));
    }

}
