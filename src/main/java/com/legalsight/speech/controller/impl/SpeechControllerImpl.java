package com.legalsight.speech.controller.impl;

import com.legalsight.speech.controller.SpeechController;
import com.legalsight.speech.domain.Speech;
import com.legalsight.speech.dto.SpeechFilterRequest;
import com.legalsight.speech.dto.SpeechesResponse;
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
        return ResponseEntity.ok(service.findSpeechById(id));
    }

    @Override
    public ResponseEntity<Speech> create(final Speech speech) {
        return ResponseEntity.ok(service.createSpeech(speech));
    }

    @Override
    public ResponseEntity<Speech> update(final String id, final Speech speech) {
        speech.setId(id);
        return ResponseEntity.ok(service.updateSpeech(speech));
    }

    @Override
    public ResponseEntity<Void> delete(final String id) {
        service.deleteSpeech(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<SpeechesResponse> search(final String author,
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
        return ResponseEntity.ok(service.searchSpeeches(request));
    }

    @Override
    public ResponseEntity<SpeechesResponse> findAll(final int perPage,
                                                    final int page,
                                                    final String sortBy,
                                                    final String sortDir) {
        SpeechFilterRequest request = new SpeechFilterRequest();
        request.setPage(page);
        request.setPerPage(perPage);
        request.setSortBy(sortBy);
        request.setSortDir(sortDir);
        return ResponseEntity.ok(service.searchSpeeches(request));
    }

}
