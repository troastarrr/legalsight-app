package com.legalsight.controller.impl;

import com.legalsight.speech.controller.impl.SpeechControllerImpl;
import com.legalsight.speech.domain.Speech;
import com.legalsight.speech.dto.SpeechFilterRequest;
import com.legalsight.speech.dto.SpeechListResponse;
import com.legalsight.speech.service.SpeechService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SpeechControllerImplTest {

    @Mock
    private SpeechService speechService;

    @InjectMocks
    private SpeechControllerImpl speechController;

    @Test
    @DisplayName("Given speech ID, when finding speech, then return correct speech")
    void givenSpeechId_whenFindingSpeech_thenReturnCorrectSpeech() {
        // Given
        String speechId = "123";
        Speech expectedSpeech = new Speech();
        when(speechService.findById(speechId)).thenReturn(expectedSpeech);

        // When
        ResponseEntity<Speech> response = speechController.find(speechId);

        // Then
        assertThat(response.getBody()).isEqualTo(expectedSpeech);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(speechService, times(1)).findById(speechId);
    }

    @Test
    @DisplayName("Given speech to add, when adding speech, then return added speech")
    void givenSpeechToAdd_whenAddingSpeech_thenReturnAddedSpeech() {
        // Given
        Speech speechToAdd = new Speech();
        Speech expectedSpeech = new Speech();
        when(speechService.add(speechToAdd)).thenReturn(expectedSpeech);

        // When
        ResponseEntity<Speech> response = speechController.add(speechToAdd);

        // Then
        assertThat(response.getBody()).isEqualTo(expectedSpeech);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(speechService, times(1)).add(speechToAdd);
    }

    @Test
    @DisplayName("Given speech ID and updated speech, when updating speech, then return updated speech")
    void givenSpeechIdAndUpdatedSpeech_whenUpdatingSpeech_thenReturnUpdatedSpeech() {
        // Given
        String speechId = "123";
        Speech speechToUpdate = new Speech();
        Speech expectedSpeech = new Speech();
        when(speechService.update(speechToUpdate)).thenReturn(expectedSpeech);

        // When
        ResponseEntity<Speech> response = speechController.update(speechId, speechToUpdate);

        // Then
        assertThat(response.getBody()).isEqualTo(expectedSpeech);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(speechService, times(1)).update(speechToUpdate);
    }

    @Test
    @DisplayName("Given speech ID, when deleting speech, then return no content")
    void givenSpeechId_whenDeletingSpeech_thenReturnNoContent() {
        // Given
        String speechId = "123";

        // When
        ResponseEntity<Void> response = speechController.delete(speechId);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(speechService, times(1)).deleteSpeech(speechId);
    }

    @Test
    @DisplayName("Given search parameters, when searching speeches, then return filtered speeches")
    void givenSearchParameters_whenSearchingSpeeches_thenReturnFilteredSpeeches() {
        // Given
        String author = "John Doe";
        String text = "sample text";
        String subjectArea = "law";
        LocalDate speechDate = LocalDate.now();
        int perPage = 10;
        int page = 1;
        String sortBy = "speechDate";
        String sortDir = "asc";

        SpeechFilterRequest filterRequest = new SpeechFilterRequest();
        Speech speech = new Speech();
        speech.setAuthor(author);
        speech.setSpeechText(text);
        speech.setSubjectArea(subjectArea);
        speech.setSpeechDate(speechDate);
        filterRequest.setSpeech(speech);
        filterRequest.setPerPage(perPage);
        filterRequest.setPage(page);
        filterRequest.setSortBy(sortBy);
        filterRequest.setSortDir(sortDir);

        List<Speech> expectedSpeeches = Arrays.asList(new Speech(), new Speech());
        SpeechListResponse expectedResponse = new SpeechListResponse(expectedSpeeches);
        when(speechService.findByFilter(any(SpeechFilterRequest.class))).thenReturn(expectedResponse);

        // When
        ResponseEntity<SpeechListResponse> response = speechController.search(
                author, text, subjectArea, speechDate, perPage, page, sortBy, sortDir
        );

        // Then
        assertThat(response.getBody()).isEqualTo(expectedResponse);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(speechService, times(1)).findByFilter(any(SpeechFilterRequest.class));
    }

    @Test
    @DisplayName("Given no parameters, when retrieving all speeches, then return all speeches")
    void givenNoParameters_whenRetrievingAllSpeeches_thenReturnAllSpeeches() {
        // Given
        int perPage = 10;
        int page = 1;
        String sortBy = "speechDate";
        String sortDir = "asc";

        SpeechFilterRequest filterRequest = new SpeechFilterRequest();
        filterRequest.setPerPage(perPage);
        filterRequest.setPage(page);
        filterRequest.setSortBy(sortBy);
        filterRequest.setSortDir(sortDir);

        List<Speech> expectedSpeeches = Arrays.asList(new Speech(), new Speech());
        SpeechListResponse expectedResponse = new SpeechListResponse(expectedSpeeches);
        when(speechService.findByFilter(any(SpeechFilterRequest.class))).thenReturn(expectedResponse);

        // When
        ResponseEntity<SpeechListResponse> response = speechController.findAll(
                perPage, page, sortBy, sortDir
        );

        // Then
        assertThat(response.getBody()).isEqualTo(expectedResponse);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(speechService, times(1)).findByFilter(any(SpeechFilterRequest.class));
    }

}

